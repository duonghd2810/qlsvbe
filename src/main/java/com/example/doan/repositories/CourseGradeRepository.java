package com.example.doan.repositories;

import com.example.doan.models.CourseGrade;
import com.example.doan.models.CourseGradeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CourseGradeRepository extends JpaRepository<CourseGrade,Long> {
    CourseGrade findCourseGradeByCourseGradeId(CourseGradeId courseGradeId);

    @Query(value = "select * from course_grade where student_id = ?1",nativeQuery = true)
    List<CourseGrade> findByStudent(Long idStudent);

    @Query(value ="select * from course_grade where class_section_id = ?1",nativeQuery = true)
    List<CourseGrade> getCourseGradeByIdClass(Long idClass);

    @Modifying
    @Transactional
    @Query(value = "delete from COURSE_GRADE where class_section_id = ?1 and student_id = ?2",nativeQuery = true)
    void deleteClassSectionRegisted(Long idClassSection, Long idStudent);
}
