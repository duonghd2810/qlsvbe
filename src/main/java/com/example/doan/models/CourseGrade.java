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

    private Double hs1;

    private Double hs2;

    private Double hs3;

    private Double hs4;

    private Double hs5;

    private Long sotietnghi;

    private Double finaltest;

    public CourseGrade(CourseGradeId courseGradeId) {
        this.courseGradeId = courseGradeId;
    }
}
