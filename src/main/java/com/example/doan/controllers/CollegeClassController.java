package com.example.doan.controllers;

import com.example.doan.bases.BaseController;
import com.example.doan.dtos.CollegeClassDTO;
import com.example.doan.models.CollegeClass;
import com.example.doan.services.ICollegeClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/class")
public class CollegeClassController extends BaseController<CollegeClass> {
    @Autowired
    private ICollegeClassService collegeClassService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return this.resListSuccess(collegeClassService.getAllClass());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id")Long id){
        return this.resSuccess(collegeClassService.getClassById(id));
    }
    @PostMapping("/create")
    public ResponseEntity<?> createClass(@RequestBody CollegeClassDTO collegeClassDTO){
        return this.resSuccess(collegeClassService.createClass(collegeClassDTO));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateClass(@RequestBody CollegeClassDTO collegeClassDTO,
                                         @PathVariable(name = "id")Long id){
        return this.resSuccess(collegeClassService.updateClass(collegeClassDTO,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClass(@PathVariable(name = "id")Long id){
        return this.resStringSuccess(collegeClassService.deleteClass(id));
    }
}
