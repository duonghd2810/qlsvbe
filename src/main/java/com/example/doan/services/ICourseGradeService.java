package com.example.doan.services;

import com.example.doan.dtos.CourseGradeDTO;
import com.example.doan.dtos.ReponseStudentByClassSection;
import com.example.doan.dtos.ResponseCourseForStudent;
import com.example.doan.models.CourseGrade;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICourseGradeService {
    List<ResponseCourseForStudent> getAllCourseForStudent(Long idStudent);
    List<ReponseStudentByClassSection> getAllStudentForClassSection(Long idClass);
    CourseGrade registClassSection(Long idClassSection, Long idStudent);
    CourseGrade enterPoint(Long idClassSection, Long idStudent, CourseGradeDTO courseGradeDTO);
    void savePointForStudentToDb(MultipartFile file, Long idClass);
}
