package com.example.doan.controllers;

import com.example.doan.bases.BaseController;
import com.example.doan.dtos.CourseGradeDTO;
import com.example.doan.models.CourseGrade;
import com.example.doan.services.ICourseGradeService;
import com.example.doan.services.imp.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

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

    @GetMapping("/tkb/{idStudent}")
    public ResponseEntity<?> getTKBByStudent(@PathVariable(name = "idStudent")Long idStudent){
        return ResponseEntity.status(HttpStatus.OK.value()).body(iCourseGradeService.listTKBByStudent(idStudent));
    }

    @GetMapping("/export/{idClass}")
    public ResponseEntity<?> exportStudent(@PathVariable(name = "idClass")Long idClass) throws IOException {
        ByteArrayInputStream data = reportService.generalExcel(idClass);
        InputStreamResource file = new InputStreamResource(data);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header("Content-Disposition", "attachment;").body(file);

    }

    @GetMapping("/list-regist/{idStudent}")
    public ResponseEntity<?> getListClassSectionRegistedByStudent(@PathVariable(name = "idStudent")Long idStudent){
        return ResponseEntity.status(HttpStatus.OK.value()).body(iCourseGradeService.listCourseRegistedByStudent(idStudent));
    }

    @PostMapping("/import/{idClass}")
    public ResponseEntity<?> importPointStudent(@PathVariable(name = "idClass")Long idClass,
                                                @RequestParam("file")MultipartFile file) {
        iCourseGradeService.savePointForStudentToDb(file,idClass);
        return ResponseEntity.ok(Map.of("Message"," Nhập điểm thành phần thành công"));
    }

    @PostMapping("/final-point")
    public ResponseEntity<?> importFinalPointStudent(@RequestParam("file")MultipartFile file) {
        iCourseGradeService.saveFinalPointForStudent(file);
        return ResponseEntity.ok(Map.of("Message"," Nhập điểm cuối kỳ thành công"));
    }

    @GetMapping("/detailclass/{idClass}")
    public ResponseEntity<?> getAllStudentByClass(@PathVariable(name = "idClass")Long idClass){
        return ResponseEntity.status(HttpStatus.OK.value()).body(iCourseGradeService.getAllStudentForClassSection(idClass));
    }

    @DeleteMapping("/{idStudent}/cancel-registed/{idClass}")
    public ResponseEntity<?> cancelRegistedByStudent(@PathVariable(name = "idStudent")Long idStudent,
                                                     @PathVariable(name = "idClass")Long idClass){
        return this.resStringSuccess(iCourseGradeService.deleteCourseGradeRegisted(idStudent,idClass));
    }

    @PostMapping("/{idClass}/registclasssection/{idStudent}")
    public ResponseEntity<?> registClassSection(@PathVariable(name = "idClass")Long idClassSection,
                                                @PathVariable(name = "idStudent")Long idStudent){
        return this.resSuccess(iCourseGradeService.registClassSection(idClassSection,idStudent));
    }
}
