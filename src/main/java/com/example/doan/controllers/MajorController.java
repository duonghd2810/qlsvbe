package com.example.doan.controllers;

import com.example.doan.bases.BaseController;
import com.example.doan.dtos.MajorDTO;
import com.example.doan.models.Major;
import com.example.doan.services.IMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/major")
public class MajorController extends BaseController<Major> {
    @Autowired
    private IMajorService majorService;

    @GetMapping
    public ResponseEntity<?> getAllMajor(){
        return this.resListSuccess(majorService.getListMajor());
    }
    @GetMapping("/{id_major}")
    public ResponseEntity<?> getMajorById(@PathVariable(name = "id_major") Long id){
        return this.resSuccess(majorService.getMajorById(id));
    }
    @PostMapping("/create")
    public ResponseEntity<?> createMajor(@RequestBody MajorDTO majorDTO){
        return this.resSuccess(majorService.createMajor(majorDTO));
    }
    @PatchMapping("/update/{id_major}")
    public ResponseEntity<?> createMajor(@RequestBody MajorDTO majorDTO,
                                         @PathVariable(name = "id_major") Long id){
        return this.resSuccess(majorService.updateMajor(majorDTO,id));
    }
    @DeleteMapping("/delete/{id_major}")
    public ResponseEntity<?> deleteMajor(@PathVariable(name = "id_major") Long id){
        return ResponseEntity.status(HttpStatus.OK.value()).body(majorService.deleteMajor(id));
    }
}
