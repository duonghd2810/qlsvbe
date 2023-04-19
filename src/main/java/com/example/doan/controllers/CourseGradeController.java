package com.example.doan.controllers;

import com.example.doan.bases.BaseController;
import com.example.doan.dtos.CourseGradeDTO;
import com.example.doan.models.CourseGrade;
import com.example.doan.services.ICourseGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/coursegrade")
public class CourseGradeController extends BaseController<CourseGrade> {
    @Autowired
    private ICourseGradeService iCourseGradeService;

    @GetMapping("/studyresult/{idStudent}")
    public ResponseEntity<?> getAllCourseByStudent(@PathVariable(name = "idStudent")Long idStudent){
        return ResponseEntity.status(HttpStatus.OK.value()).body(iCourseGradeService.getAllCourseForStudent(idStudent));
    }
    @PostMapping("/{idClass}/registclasssection/{idStudent}")
    public ResponseEntity<?> registClassSection(@PathVariable(name = "idClass")Long idClassSection,
                                                @PathVariable(name = "idStudent")Long idStudent){
        return this.resSuccess(iCourseGradeService.registClassSection(idClassSection,idStudent));
    }
    @PatchMapping("/{idClass}/enterpoint/{idStudent}")
    public ResponseEntity<?> enterPoint(@PathVariable(name = "idClass")Long idClassSection,
                                        @PathVariable(name = "idStudent")Long idStudent,
                                        @RequestBody CourseGradeDTO courseGradeDTO){
        return this.resSuccess(iCourseGradeService.enterPoint(idClassSection,idStudent,courseGradeDTO));
    }
}
