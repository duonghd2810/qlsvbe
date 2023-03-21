package com.example.doan.dtos;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String fullname;

    private Timestamp dateOfBirth;

    private String phone;

    private String address;

    private String email;

    private String gender;

    private String username;

    private String password;

    private String avatar;
}
