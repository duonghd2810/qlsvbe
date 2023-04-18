package com.example.doan.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String fullName;

    private Date dateOfBirth;

    private String phone;

    private String address;

    private String email;

    private String gender;

    private String username;

    private String avatar;
}
