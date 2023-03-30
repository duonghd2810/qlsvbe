package com.example.doan.services.imp;

import com.example.doan.dtos.UserDTO;
import com.example.doan.exceptions.BadRequestException;
import com.example.doan.models.Role;
import com.example.doan.payload.AuthenticationRequest;
import com.example.doan.payload.AuthenticationResponse;
import com.example.doan.models.User;
import com.example.doan.repositories.RoleRepository;
import com.example.doan.repositories.UserRepository;
import com.example.doan.services.IAuthService;
import com.example.doan.utils.ConvertObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Override
    public AuthenticationResponse register(UserDTO userDTO) {
       /* var user = new User();
        var newUser =  ConvertObject.convertUserDTOToUser(userDTO,user);
        userRepository.save(newUser);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(newUser.getId())
                .fullName(newUser.getFullName())
                .roleSet(newUser.getRoless())
                .build();*/
        return new AuthenticationResponse();
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
        return new AuthenticationResponse(jwt,user.getId(),user.getEmail(),roles);
    }
}
