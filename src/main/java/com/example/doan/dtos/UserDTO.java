package com.example.doan.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
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
