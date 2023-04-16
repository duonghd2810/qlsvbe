package com.example.doan.services;

import com.example.doan.dtos.CourseGradeDTO;
import com.example.doan.models.CourseGrade;

public interface ICourseGradeService {
    CourseGrade registClassSection(Long idClassSection, Long idStudent);
    CourseGrade enterPoint(Long idClassSection, Long idStudent, CourseGradeDTO courseGradeDTO);
}
