package com.example.doan.services;

import com.example.doan.dtos.CourseGradeDTO;
import com.example.doan.dtos.ReponseStudentByClassSection;
import com.example.doan.dtos.ResponseCourseForStudent;
import com.example.doan.models.CourseGrade;

import java.util.List;

public interface ICourseGradeService {
    List<ResponseCourseForStudent> getAllCourseForStudent(Long idStudent);
    List<ReponseStudentByClassSection> getAllCourseForClassSection(Long idClass);
    CourseGrade registClassSection(Long idClassSection, Long idStudent);
    CourseGrade enterPoint(Long idClassSection, Long idStudent, CourseGradeDTO courseGradeDTO);
}
