package com.example.doan.repositories;

import com.example.doan.models.ClassSection;
import com.example.doan.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassSectionRepository extends JpaRepository<ClassSection,Long> {
    @Query(value = "select cs.id_subject, s.price, s.subject_code, s.subject_name, s.tc, cs.id, cs.ma_hp , cs.id_teacher from subjects s join class_section cs on s.id = cs.id_subject " +
            "where cs.id_subject = ?1",nativeQuery = true)
    List<ClassSection> getListClassByIdSubject(Long idSubject);
    @Query(value = "select cs.id_subject, s.price, s.subject_name, s.tc, cs.id, cs.ma_hp , cs.id_teacher from subjects s join class_section cs on s.id = cs.id_subject " +
            "order by s.subject_name",nativeQuery = true)
    List<ClassSection> getListClassSection();
    List<ClassSection> getAllByTeacher(User teacher);
}
