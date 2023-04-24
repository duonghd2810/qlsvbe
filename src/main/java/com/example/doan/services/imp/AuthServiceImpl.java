package com.example.doan.services.imp;

import com.example.doan.dtos.UserDTO;
import com.example.doan.exceptions.BadRequestException;
import com.example.doan.exceptions.NotFoundException;
import com.example.doan.models.Major;
import com.example.doan.models.Role;
import com.example.doan.payload.AuthenticationRequest;
import com.example.doan.payload.AuthenticationResponse;
import com.example.doan.models.User;
import com.example.doan.repositories.MajorRepository;
import com.example.doan.repositories.RoleRepository;
import com.example.doan.repositories.UserRepository;
import com.example.doan.services.IAuthService;
import com.example.doan.services.IMailService;
import com.example.doan.utils.ConvertObject;
import com.example.doan.utils.GenaralDataUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private JwtService jwtService;
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
        Optional<Major> major = majorRepository.findById(userDTO.getId_major());
        if(major.isEmpty()){
            throw new NotFoundException("Ngành học không tồn tại");
        }
        User oldUser = new User();
        String username = GenaralDataUser.generateStudent();
        do {
            oldUser = userRepository.findByUsername(username);
            if(oldUser != null){
                username = GenaralDataUser.generateStudent();
            }
        }while(oldUser != null);
        String password = GenaralDataUser.generatePassword();
        User user = new User();
        ConvertObject.convertUserDTOToUser(userDTO,user);
        user.setMajor(major.get());
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        StringBuilder content = new StringBuilder("Thông tin tài khoản \n");
        content.append("Tên đăng nhập:  ");
        content.append(username);
        content.append("\n");
        content.append("Mât khẩu:  ");
        content.append(password);
        mailService.sendMailWithText("Thông tin sinh viên "+ user.getFullName(),content.toString(),user.getEmail());
        Role role = roleRepository.findByRoleName("STUDENT");
        user.setRoless(Set.of(role));
        User newUser = userRepository.save(user);
        Set<User> users = role.getUserss();
        users.add(user);
        role.setUserss(users);
        roleRepository.save(role);
        final UserDetails userDetails = myUserDetailService.loadUserByUsername(newUser.getUsername());
        final String jwt = jwtService.generateToken(userDetails);

        return new AuthenticationResponse(jwt, newUser.getId(), newUser.getFullName(), newUser.getAvatar(),List.of(role.getRoleName()));
    }

    @Override
    public AuthenticationResponse registerTeacher(UserDTO userDTO) {
        Optional<Major> major = majorRepository.findById(userDTO.getId_major());
        if(major.isEmpty()){
            throw new NotFoundException("Ngành học không tồn tại");
        }
        User oldUser = new User();
        String username = GenaralDataUser.generateTeacher();
        do {
            oldUser = userRepository.findByUsername(username);
            if(oldUser != null){
                username = GenaralDataUser.generateTeacher();
            }
        }while(oldUser != null);
        String password = GenaralDataUser.generatePassword();
        User user = new User();
        ConvertObject.convertUserDTOToUser(userDTO,user);
        user.setMajor(major.get());
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        StringBuilder content = new StringBuilder("Thông tin tài khoản \n");
        content.append("Tên đăng nhập:  ");
        content.append(username);
        content.append("\n");
        content.append("Mât khẩu:  ");
        content.append(password);
        mailService.sendMailWithText("Thông tin giáo viên "+ user.getFullName(),content.toString(),user.getEmail());
        Role role = roleRepository.findByRoleName("TEACHER");
        user.setRoless(Set.of(role));
        User newUser = userRepository.save(user);
        Set<User> users = role.getUserss();
        users.add(user);
        role.setUserss(users);
        roleRepository.save(role);
        final UserDetails userDetails = myUserDetailService.loadUserByUsername(newUser.getUsername());
        final String jwt = jwtService.generateToken(userDetails);

        return new AuthenticationResponse(jwt, newUser.getId(), newUser.getFullName(), newUser.getAvatar(), List.of(role.getRoleName()));
    }

    @Override
    public AuthenticationResponse registerAdmin(UserDTO userDTO) {
        User oldUser = new User();
        String username = GenaralDataUser.generateAdmin();
        do {
            oldUser = userRepository.findByUsername(username);
            if(oldUser != null){
                username = GenaralDataUser.generateAdmin();
            }
        }while(oldUser != null);
        String password = GenaralDataUser.generatePassword();
        User user = new User();
        ConvertObject.convertUserDTOToUser(userDTO,user);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        StringBuilder content = new StringBuilder("Thông tin tài khoản \n");
        content.append("Tên đăng nhập:  ");
        content.append(username);
        content.append("\n");
        content.append("Mât khẩu:  ");
        content.append(password);
        mailService.sendMailWithText("Thông tin admin",content.toString(),user.getEmail());
        Role role = roleRepository.findByRoleName("ADMIN");
        user.setRoless(Set.of(role));
        User newUser = userRepository.save(user);
        Set<User> users = role.getUserss();
        users.add(user);
        role.setUserss(users);
        roleRepository.save(role);
        final UserDetails userDetails = myUserDetailService.loadUserByUsername(newUser.getUsername());
        final String jwt = jwtService.generateToken(userDetails);

        return new AuthenticationResponse(jwt, newUser.getId(), newUser.getFullName(), newUser.getAvatar(), List.of(role.getRoleName()));
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),request.getPassword()
            ));
        }catch(BadCredentialsException ex){
            throw new BadRequestException("Incorrect username or password");
        }
        final UserDetails userDetails = myUserDetailService.loadUserByUsername(request.getUsername());
        final String jwt = jwtService.generateToken(userDetails);
        User user = userRepository.findByUsername(request.getUsername());
        List<String> roles = new ArrayList<>();
        Set<Role> roleSet = user.getRoless();
        if(roleSet.size() > 0)
            roleSet.forEach(item -> roles.add(item.getRoleName()));
        return new AuthenticationResponse(jwt,user.getId(),user.getFullName(),user.getAvatar(),roles);
    }
}
