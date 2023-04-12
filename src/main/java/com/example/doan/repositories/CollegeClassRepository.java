package com.example.doan.repositories;

import com.example.doan.models.CollegeClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CollegeClassRepository extends JpaRepository<CollegeClass,Long> {
    @Query(value ="select new com.example.doan.dtos.ClassMajorDTO(cl.id,cl.className,cl.homeroomTeacher,cl.id_major,m.majorName)" +
            " from major m join college_class cl on m.id = cl.id_major",
            nativeQuery = true)
    List<CollegeClass> getAllClass();
}
