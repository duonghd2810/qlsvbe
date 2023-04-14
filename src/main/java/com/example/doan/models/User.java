package com.example.doan.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
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
    private Long id;

    @Nationalized
    private String fullName;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

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

    @ManyToMany(mappedBy = "userss",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Role> roless;

    @OneToMany(mappedBy = "studentCourse")
    private List<CourseGrade> courseGrades;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "listTeacher")
    private List<ClassSection> classSections;
}
