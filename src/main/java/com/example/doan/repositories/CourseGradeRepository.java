package com.example.doan.repositories;

import com.example.doan.models.ClassSection;
import com.example.doan.models.CourseGrade;
import com.example.doan.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseGradeRepository extends JpaRepository<CourseGrade,Long> {
    CourseGrade getCourseGradeByClassSection(ClassSection classSection);
    CourseGrade getCourseGradeByClassSectionAndAndStudentCourse(ClassSection classSection, User student);
}
