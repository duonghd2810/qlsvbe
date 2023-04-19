package com.example.doan.payload;

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
    private String avatar;
    private Long majorId;
    private List<String> roleSet;

    public AuthenticationResponse(String token, Long userId, String fullName, String avatar, List<String> roleSet) {
        this.token = token;
        this.userId = userId;
        this.fullName = fullName;
        this.avatar = avatar;
        this.roleSet = roleSet;
    }
}
