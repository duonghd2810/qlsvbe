package com.example.doan.repositories;

import com.example.doan.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
    @Query(value = "select s.id, s.subject_code, s.subject_name, s.price, s.tc, s.id_major,m.major_name, s.create_at, s.update_at " +
            "from major m join subjects s on m.id = s.id_major",nativeQuery = true)
    List<Subject> getAllSubject();
    Subject findBySubjectCode(String subjectCode);
}
