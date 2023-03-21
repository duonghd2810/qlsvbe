package com.example.doan.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Nationalized
    private String fullName;

    private Timestamp dateOfBirth;

    private String phone;

    @Nationalized
    private String address;

    private String email;

    @Nationalized
    private String gender;

    private String username;

    private String password;

    private String avatar;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "userss")
    private Set<Role> roless;
}
