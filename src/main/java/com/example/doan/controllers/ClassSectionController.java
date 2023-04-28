package com.example.doan.controllers;

import com.example.doan.bases.BaseController;
import com.example.doan.dtos.ClassSectionUpdDTO;
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
    private IClassSectionService iClassSectionService;

    @GetMapping
    public ResponseEntity<?> getAllClassSection(){
        return ResponseEntity.status(HttpStatus.OK.value()).body(iClassSectionService.getAllClassSection());
    }
    @GetMapping("/classdetail/{id}")
    public ResponseEntity<?> getClassSectionByIdClass(@PathVariable(name = "id")Long id){
        return ResponseEntity.status(HttpStatus.OK.value()).body(iClassSectionService.getClassSectionById(id));
    }
    @GetMapping("/{idStudent}")
    public ResponseEntity<?> getListClassSectionByStudent(@PathVariable(name = "idStudent")Long idStudent){
        return ResponseEntity.status(HttpStatus.OK.value()).body(iClassSectionService.getListClassSectionByStudent(idStudent));
    }
    @GetMapping("/teacher/{id}")
    public ResponseEntity<?> getAllClassSectionByTeacher(@PathVariable(name = "id")Long id){
        return ResponseEntity.status(HttpStatus.OK.value()).body(iClassSectionService.getListByTeacher(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> createClassSection(@PathVariable(name = "id")Long id){
        return ResponseEntity.status(HttpStatus.OK.value()).body(iClassSectionService.createClassSection(id));
    }
    @PatchMapping("/updateClass/{idClassSection}")
    public ResponseEntity<?> updateClassSection(@PathVariable(name = "idClassSection")Long idClassSection,
                                                @RequestBody ClassSectionUpdDTO classSectionUpdDTO){
        return this.resSuccess(iClassSectionService.updateClassSection(idClassSection,classSectionUpdDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClassSection(@PathVariable(name = "id")Long idClassSection){
        return this.resStringSuccess(iClassSectionService.deleteClassSection(idClassSection));
    }
}
