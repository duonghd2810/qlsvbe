package com.example.doan.controllers;

import com.example.doan.bases.BaseController;
import com.example.doan.dtos.SubjectDTO;
import com.example.doan.models.Subject;
import com.example.doan.services.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectController extends BaseController<Subject> {
    @Autowired
    private ISubjectService subjectService;

    @GetMapping
    public ResponseEntity<?> getAllSubject(){
        return this.resListSuccess(subjectService.getAllSubject());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getSubjectById(@PathVariable(name = "id")Long id){
        return this.resSuccess(subjectService.getSubjectById(id));
    }
    @PostMapping
    public ResponseEntity<?> createSubject(@RequestBody SubjectDTO subjectDTO){
        return this.resSuccess(subjectService.createSubject(subjectDTO));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateSubject(@RequestBody SubjectDTO subjectDTO,
                                           @PathVariable(name = "id")Long id){
        return this.resSuccess(subjectService.updateSubject(subjectDTO,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delelteSubject(@PathVariable(name = "id")Long id){
        return this.resStringSuccess(subjectService.deleteSubject(id));
    }
}
