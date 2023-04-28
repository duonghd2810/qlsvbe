package com.example.doan.services;

import com.example.doan.dtos.CourseRegistedByStudent;
import com.example.doan.dtos.ReponseStudentByClassSection;
import com.example.doan.dtos.ResponseCourseForStudent;
import com.example.doan.models.CourseGrade;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICourseGradeService {
    List<ResponseCourseForStudent> getAllCourseForStudent(Long idStudent);
    List<ReponseStudentByClassSection> getAllStudentForClassSection(Long idClass);
    CourseGrade registClassSection(Long idClassSection, Long idStudent);
    List<CourseRegistedByStudent> listCourseRegistedByStudent(Long idStudent);
    String deleteCourseGradeRegisted(Long idStudent,Long idClassSection);
    void savePointForStudentToDb(MultipartFile file, Long idClass);
    void saveFinalPointForStudent(MultipartFile file);
}
