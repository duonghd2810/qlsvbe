package com.example.doan.repositories;

import com.example.doan.models.CourseGrade;
import com.example.doan.models.CourseGradeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseGradeRepository extends JpaRepository<CourseGrade,Long> {
    CourseGrade findCourseGradeByCourseGradeId(CourseGradeId courseGradeId);

    @Query(value = "select * from course_grade where student_id = ?1",nativeQuery = true)
    List<CourseGrade> findByStudent(Long idStudent);
}
