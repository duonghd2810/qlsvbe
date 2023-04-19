package com.example.doan.controllers;

import com.example.doan.bases.BaseController;
import com.example.doan.models.ClassSection;
import com.example.doan.services.IClassSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/classsection")
public class ClassSectionController extends BaseController<ClassSection> {
    @Autowired
    private IClassSectionService iClassSection;

    @GetMapping
    public ResponseEntity<?> getAllClassSection(){
        return ResponseEntity.status(HttpStatus.OK.value()).body(iClassSection.getAllClassSection());
    }
    @GetMapping("/{idStudent}")
    public ResponseEntity<?> getListClassSectionByMajor(@PathVariable(name = "idStudent")Long idStudent){
        return ResponseEntity.status(HttpStatus.OK.value()).body(iClassSection.getListClassSectionByStudent(idStudent));
    }
    @GetMapping("/teacher/{id}")
    public ResponseEntity<?> getAllClassSectionByTeacher(@PathVariable(name = "id")Long id){
        return this.resListSuccess(iClassSection.getListByTeacher(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> createClassSection(@PathVariable(name = "id")Long id){
        return ResponseEntity.status(HttpStatus.OK.value()).body(iClassSection.createClassSection(id));
    }
    @PatchMapping("/{id}/teacher/{idTeacher}")
    public ResponseEntity<?> updateClassSection(@PathVariable(name = "id")Long idClassSection,
                                                @PathVariable(name = "idTeacher")Long idteacher){
        return this.resSuccess(iClassSection.updateTeacherForClass(idClassSection,idteacher));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClassSection(@PathVariable(name = "id")Long idClassSection){
        return this.resStringSuccess(iClassSection.deleteClassSection(idClassSection));
    }
}
