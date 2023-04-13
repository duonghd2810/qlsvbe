package com.example.doan.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "class_section")
public class ClassSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_subject")
    @JsonIgnore
    private Subject subjectt;

    @OneToMany(mappedBy = "classSection")
    private List<CourseGrade> courseGrades;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "teacher_subject",
            joinColumns = @JoinColumn(name = "id_subject"),
            inverseJoinColumns = @JoinColumn(name = "id_teacher"))
    @JsonIgnore
    private List<User> listTeacher;
}
