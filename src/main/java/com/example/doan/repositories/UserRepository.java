package com.example.doan.repositories;

import com.example.doan.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
    @Query(value = "SELECT * FROM USERS u JOIN ROLE_USER ru ON u.id = ru.id_user " +
            "JOIN ROLES r ON ru.id_role = r.id" +
            " WHERE r.role_name = 'STUDENT'",
            nativeQuery = true)
    List<User> getAllStudent();

    @Query(value = "SELECT * FROM USERS u JOIN ROLE_USER ru ON u.id = ru.id_user " +
            "JOIN ROLES r ON ru.id_role = r.id" +
            " WHERE r.role_name = 'TEACHER'",
            nativeQuery = true)
    List<User> getAllTeacher();

    @Query(value = "SELECT * FROM USERS u JOIN ROLE_USER ru ON u.id = ru.id_user " +
            "JOIN ROLES r ON ru.id_role = r.id" +
            " JOIN MAJOR m on u.id_major = m.id" +
            " WHERE r.role_name = 'STUDENT'",
            nativeQuery = true)
    List<User> getAllStudentByMajor();

    @Modifying
    @Transactional
    @Query(value = "delete from ROLE_USER where id_user = ?1 and id_role = ?2",nativeQuery = true)
    void deleteRecordInRoleUser(Long idUser,Long idRole);
}
