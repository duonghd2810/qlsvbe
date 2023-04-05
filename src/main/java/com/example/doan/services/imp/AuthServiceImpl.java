package com.example.doan.services.imp;

import com.example.doan.dtos.UserDTO;
import com.example.doan.exceptions.BadRequestException;
import com.example.doan.exceptions.DuplicateException;
import com.example.doan.models.Role;
import com.example.doan.payload.AuthenticationRequest;
import com.example.doan.payload.AuthenticationResponse;
import com.example.doan.models.User;
import com.example.doan.repositories.RoleRepository;
import com.example.doan.repositories.UserRepository;
import com.example.doan.services.IAuthService;
import com.example.doan.services.IMailService;
import com.example.doan.utils.ConvertObject;
import com.example.doan.utils.GenaralDataUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    private IMailService mailService;

    @Override
    public AuthenticationResponse registerStudent(UserDTO userDTO) {
        String username = GenaralDataUser.generateUsername();
        String password = GenaralDataUser.generatePassword();
        User oldUser = userRepository.findByUsername(username);
        if(oldUser != null){
            throw new DuplicateException("User has exists");
        }
        User user = new User();
        ConvertObject.convertUserDTOToUser(userDTO,user);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        Role role = roleRepository.findByRoleName("STUDENT");
        user.setRoless(Set.of(role));
        User newUser = userRepository.save(user);
        Set<User> users = role.getUserss();
        users.add(user);
        role.setUserss(users);
        roleRepository.save(role);
        final UserDetails userDetails = myUserDetailService.loadUserByUsername(newUser.getUsername());
        final String jwt = jwtService.generateToken(userDetails);

        return new AuthenticationResponse(jwt, newUser.getId(), newUser.getUsername(), newUser.getAvatar(), List.of(role.getRoleName()));
    }

    @Override
    public AuthenticationResponse registerAdmin(UserDTO userDTO) {
        return null;
    }

    @Override
    public AuthenticationResponse registerTeacher(UserDTO userDTO) {
        return null;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),request.getPassword()
            ));
        }catch(BadCredentialsException ex){
            throw new BadRequestException("Incorrect email or password");
        }
        final UserDetails userDetails = myUserDetailService.loadUserByUsername(request.getUsername());
        final String jwt = jwtService.generateToken(userDetails);
        User user = userRepository.findByUsername(request.getUsername());
        List<String> roles = new ArrayList<>();
        Set<Role> roleSet = user.getRoless();
        if(roleSet.size() > 0)
            roleSet.forEach(item -> roles.add(item.getRoleName()));
        return new AuthenticationResponse(jwt,user.getId(),user.getEmail(),user.getAvatar(),roles);
    }
}
