package com.example.doan.repositories;

import com.example.doan.models.CourseGrade;
import com.example.doan.models.CourseGradeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseGradeRepository extends JpaRepository<CourseGrade,Long> {
    CourseGrade findCourseGradeByCourseGradeId(CourseGradeId courseGradeId);
}
