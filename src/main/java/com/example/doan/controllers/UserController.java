package com.example.doan.controllers;

import com.example.doan.bases.BaseController;
import com.example.doan.dtos.UserDTO;
import com.example.doan.models.User;
import com.example.doan.services.IMailService;
import com.example.doan.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends BaseController<User> {
    @Autowired
    private IUserService userService;

    @GetMapping("/teacher")
    public ResponseEntity<?> getAllTeacher(){
        return ResponseEntity.status(HttpStatus.OK.value()).body("343");
    }
    @GetMapping("/student")
    public ResponseEntity<?> getAllStudent(){
        return ResponseEntity.status(HttpStatus.OK.value()).body("sdfd");
    }
    @GetMapping("/{id_user}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id_user")Long id){
        return this.resSuccess(userService.getUserById(id));
    }
    @PostMapping("/teacher/create")
    public ResponseEntity<?> createTeacher(@RequestBody UserDTO userDTO){
        return this.resSuccess(userService.createTeacher(userDTO));
    }
    @PostMapping("/student/create")
    public ResponseEntity<?> createStudent(@RequestBody UserDTO userDTO){
        return this.resSuccess(userService.createStudent(userDTO));
    }
}
