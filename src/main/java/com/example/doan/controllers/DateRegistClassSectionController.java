package com.example.doan.controllers;

import com.example.doan.dtos.DateRegistDTO;
import com.example.doan.services.IDateRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dateregist")
public class DateRegistClassSectionController{
    @Autowired
    private IDateRegistService iDateRegistService;

    @GetMapping
    public ResponseEntity<?> getDateRegist(){
        return ResponseEntity.status(HttpStatus.OK.value()).body(iDateRegistService.getDateRegist());
    }
    @PostMapping
    public ResponseEntity<?> setDateRegistClassSection(@RequestBody DateRegistDTO dateRegistDTO){
        return ResponseEntity.status(HttpStatus.OK.value()).body(iDateRegistService.setDateRegist(dateRegistDTO));
    }
}
