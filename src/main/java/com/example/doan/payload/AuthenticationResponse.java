package com.example.doan.payload;

import com.example.doan.models.Role;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private Long userId;
    private String fullName;
    private Set<Role> roleSet;
}
