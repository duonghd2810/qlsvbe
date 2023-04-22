package com.example.doan.controllers;

import com.example.doan.bases.BaseController;
import com.example.doan.dtos.CourseGradeDTO;
import com.example.doan.models.CourseGrade;
import com.example.doan.services.ICourseGradeService;
import com.example.doan.services.imp.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/coursegrade")
public class CourseGradeController extends BaseController<CourseGrade> {
    @Autowired
    private ICourseGradeService iCourseGradeService;
    @Autowired
    private ReportService reportService;

    @GetMapping("/studyresult/{idStudent}")
    public ResponseEntity<?> getAllCourseByStudent(@PathVariable(name = "idStudent")Long idStudent){
        return ResponseEntity.status(HttpStatus.OK.value()).body(iCourseGradeService.getAllCourseForStudent(idStudent));
    }
    @GetMapping("/export/{idClass}")
    public ResponseEntity<?> exportStudent(@PathVariable(name = "idClass")Long idClass) throws IOException {
        ByteArrayInputStream data = reportService.generalExcel(idClass);
        InputStreamResource file = new InputStreamResource(data);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .header("Content-Disposition", "attachment;filename=Bangdiem.xlsx").body(file);

    }
    @GetMapping("/detailclass/{idClass}")
    public ResponseEntity<?> getAllStudentByClass(@PathVariable(name = "idClass")Long idClass){
        return ResponseEntity.status(HttpStatus.OK.value()).body(iCourseGradeService.getAllCourseForClassSection(idClass));
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
