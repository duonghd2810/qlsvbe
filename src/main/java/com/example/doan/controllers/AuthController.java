package com.example.doan.controllers;

import com.example.doan.bases.BaseController;
import com.example.doan.dtos.UserDTO;
import com.example.doan.payload.AuthenticationRequest;
import com.example.doan.payload.AuthenticationResponse;
import com.example.doan.services.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController<AuthenticationResponse> {
    private final IAuthService authService;
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody UserDTO userDTO
    ){
        return this.resSuccess(authService.register(userDTO));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody AuthenticationRequest request
    ){
        return this.resSuccess(authService.authenticate(request));
    }
}
