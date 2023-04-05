package com.example.doan.services;

import com.example.doan.dtos.UserDTO;
import com.example.doan.payload.AuthenticationRequest;
import com.example.doan.payload.AuthenticationResponse;

public interface IAuthService {
    AuthenticationResponse registerStudent(UserDTO userDTO);
    AuthenticationResponse registerAdmin(UserDTO userDTO);
    AuthenticationResponse registerTeacher(UserDTO userDTO);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
