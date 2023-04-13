package com.example.doan.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course_grade")
public class CourseGrade {
    @EmbeddedId
    private CourseGradeId courseGradeId;

    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "id_subject")
    private ClassSection classSection;
    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "id_student")
    private User studentCourse;

    private Double hs1;

    private Double hs2;

    private Double finaltest;
}
