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

    private String maHp;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_subject")
    @JsonIgnore
    private Subject subjectt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_teacher")
    @JsonIgnore
    private User teacher;

//    @OneToMany(mappedBy = "classSection")
//    private List<CourseGrade> courseGrades;
}
