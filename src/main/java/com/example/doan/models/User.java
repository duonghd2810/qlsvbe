package com.example.doan.models;

import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_class")
    private CollegeClass collegeClass;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "userss")
    private Set<Role> roless;
}
