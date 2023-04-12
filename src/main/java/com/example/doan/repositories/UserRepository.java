package com.example.doan.repositories;

import com.example.doan.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
    @Query(value = "SELECT * FROM USERS u JOIN ROLE_USER ru ON u.id_user = ru.id_user " +
            "JOIN ROLES r ON ru.id_role = r.id_role" +
            " WHERE r.role_name = 'STUDENT'",
            nativeQuery = true)
    List<User> getAllStudent();
    @Query(value = "SELECT * FROM USERS u JOIN ROLE_USER ru ON u.id_user = ru.id_user " +
            "JOIN ROLES r ON ru.id_role = r.id_role" +
            " WHERE r.role_name = 'TEACHER'",
            nativeQuery = true)
    List<User> getAllTeacher();
    @Query(value = "SELECT * FROM USERS u JOIN ROLE_USER ru ON u.id_user = ru.id_user " +
            "JOIN ROLES r ON ru.id_role = r.id_role" +
            " WHERE r.role_name = 'STUDENT' AND u.id_class IS NULL",
            nativeQuery = true)
    List<User> getAllStudentHaveNotClass();
}
