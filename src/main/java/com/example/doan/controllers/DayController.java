package com.example.doan.controllers;

import com.example.doan.repositories.DayOfWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/days")
public class DayController {
    @Autowired
    private DayOfWeekRepository dayOfWeekRepository;

    @GetMapping
    public ResponseEntity<?> getAllDay(){
        return ResponseEntity.status(HttpStatus.OK.value()).body(dayOfWeekRepository.findAll());
    }
}
