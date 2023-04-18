package com.example.doan.controllers;

import com.example.doan.bases.BaseController;
import com.example.doan.dtos.PasswordDTO;
import com.example.doan.dtos.UserDTO;
import com.example.doan.models.User;
import com.example.doan.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends BaseController<User> {
    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return this.resListSuccess(userService.getAll());
    }
    @GetMapping("/teacher")
    public ResponseEntity<?> getAllTeacher(){
        return this.resListSuccess(userService.getAllTeacher());
    }
    @GetMapping("/student")
    public ResponseEntity<?> getAllStudent(){
        return this.resListSuccess(userService.getAllStudent());
    }
    @GetMapping("/{id_user}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id_user")Long id){
        return this.resSuccess(userService.getUserById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO,
                                        @PathVariable(name = "id")Long id){
        return this.resSuccess(userService.updateUser(userDTO,id));
    }
    @PatchMapping("/changepass/{id}")
    public ResponseEntity<?> changePassword(@RequestBody PasswordDTO passwordDTO,
                                        @PathVariable(name = "id")Long id){
        return this.resSuccess(userService.changePassword(id,passwordDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id")Long id){
        return this.resStringSuccess(userService.deleteUser(id));
    }
//    @PostMapping("/{id}/upload")
//    public ResponseEntity<?> uploadAvatar(@RequestParam("image")MultipartFile multipartFile,
//                                          @PathVariable(name = "id")Long id) throws IOException {
//        String image = userService.uploadAvatar(multipartFile,id);
//        return this.resStringSuccess(image);
//    }
}
