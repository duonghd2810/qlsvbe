package com.example.doan.controllers;

import com.example.doan.repositories.ClassRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    @Autowired
    private ClassRoomRepository classRoomRepository;

    @GetMapping
    public ResponseEntity<?> getAllClassRoom(){
        return ResponseEntity.status(HttpStatus.OK.value()).body(classRoomRepository.findAll());
    }
}
