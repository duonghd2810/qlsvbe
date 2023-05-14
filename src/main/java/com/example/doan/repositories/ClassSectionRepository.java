package com.example.doan.repositories;

import com.example.doan.models.ClassSection;
import com.example.doan.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassSectionRepository extends JpaRepository<ClassSection,Long> {
    @Query(value = "select cs.id_subject, s.price, s.subject_name, s.tc, cs.id , cs.id_teacher, cs.id_classroom, cs.id_day, cs.lesson, cs.create_at, cs.update_at, cs.status " +
            "from subjects s join class_section cs on s.id = cs.id_subject " +
            "order by cs.status,s.id_major,s.subject_name",nativeQuery = true)
    List<ClassSection> getListClassSection();

    @Query(value = "select cs.id_subject, s.price, s.subject_name, s.tc, cs.id , cs.id_teacher, cs.id_classroom, cs.id_day, cs.lesson, cs.create_at, cs.update_at, cs.status " +
            "from subjects s join class_section cs on s.id = cs.id_subject " +
            "where s.id_major = (select id_major from users where id = ?1) and " +
            "cs.id_teacher is not null and cs.id_day is not null and " +
            "cs.id_classroom is not null and cs.lesson is not null and " +
            "cs.status = 'acting' " +
            "order by cs.status, cs.id_day, cs.lesson",nativeQuery = true)
    List<ClassSection> getListClassSectionByStudent(Long idStudent);

    ClassSection getClassSectionById(Long idClass);

    @Query(value = "select cs.id_subject, s.price, s.subject_name, s.tc, cs.id , cs.id_teacher, cs.id_classroom, cs.id_day, cs.lesson, cs.create_at, cs.update_at, cs.status " +
            "from subjects s join class_section cs on s.id = cs.id_subject " +
            "where cs.id_teacher = ?1 " +
            "order by cs.status, s.subject_name",nativeQuery = true)
    List<ClassSection> getAllClassByTeacher(Long  idTeacher);

    @Query(value = "select cs.id_subject, s.price, s.subject_name, s.tc, cs.id , cs.id_teacher, s.id_major,cs.id_classroom, cs.id_day, cs.lesson, cs.create_at, cs.update_at, cs.status " +
            "from subjects s join class_section cs on s.id = cs.id_subject " +
            "where cs.id = ?1 and cs.status = 'acting' order by cs.status",nativeQuery = true)
    ClassSection getClassSectionDetail(Long idClass);
}
