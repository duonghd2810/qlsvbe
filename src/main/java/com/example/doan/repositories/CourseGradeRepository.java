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

//    @Query(value ="select cg.class_section_id, s.subject_name, s.tc, cg.hs1, cg.hs2, cg.finaltest" +
//            " from course_grade cg join class_section cs on cg.class_section_id = cs.id" +
//            " join subjects s on cs.id_subject = s.id" +
//            " where student_id = ?1",nativeQuery = true)
//    List<CourseGrade> getAllCourseByStudent(Long idStudent);
}
