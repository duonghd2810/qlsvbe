package com.example.doan.payload;

import com.example.doan.models.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private Long userId;
    private String fullName;
    private List<String> roleSet;
}
