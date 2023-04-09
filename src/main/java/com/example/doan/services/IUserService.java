package com.example.doan.services;

import com.example.doan.dtos.UserDTO;
import com.example.doan.models.User;

import java.util.List;

public interface IUserService {
    List<User> getAll();
    List<User> getAllTeacher();
    List<User> getAllStudent();
    User getUserById(Long id);
    User updateUser(UserDTO userDTO,Long id);
    String deleteUser(Long id);
}
