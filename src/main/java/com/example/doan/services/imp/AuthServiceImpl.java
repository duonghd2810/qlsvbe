package com.example.doan.services.imp;

import com.example.doan.dtos.UserDTO;
import com.example.doan.payload.AuthenticationRequest;
import com.example.doan.payload.AuthenticationResponse;
import com.example.doan.models.User;
import com.example.doan.repositories.UserRepository;
import com.example.doan.services.IAuthService;
import com.example.doan.utils.ConvertObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

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
        /*authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername());
        var jwtToken  = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(user.getId())
                .fullName(user.getFullName())
                .roleSet(user.getRoless())
                .build();*/
        return new AuthenticationResponse();
    }
}
