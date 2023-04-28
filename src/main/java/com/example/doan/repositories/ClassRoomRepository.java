package com.example.doan.repositories;

import com.example.doan.models.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRoomRepository extends JpaRepository<Classroom, String> {
}
