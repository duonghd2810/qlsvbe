package com.example.doan.repositories;

import com.example.doan.models.ClassSection;
import com.example.doan.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassSectionRepository extends JpaRepository<ClassSection,Long> {
    @Query(value = "select * from subjects s join class_section cs on s.id = cs.id_subject " +
            "where cs.id_subject = ?1",nativeQuery = true)
    List<ClassSection> getListClassByIdSubject(Long idSubject);
    List<ClassSection> getAllByTeacher(User teacher);
}
