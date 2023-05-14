package com.example.doan.models;

import com.example.doan.bases.BaseEntity;
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
public class CourseGrade extends BaseEntity {
    public CourseGrade(CourseGradeId courseGradeId) {
        this.courseGradeId = courseGradeId;
    }

    public CourseGrade(Double hs1, Double hs2, Double hs3, Double hs4, Double hs5, Long sotietnghi) {
        this.courseGradeId = courseGradeId;
        this.hs1 = hs1;
        this.hs2 = hs2;
        this.hs3 = hs3;
        this.hs4 = hs4;
        this.hs5 = hs5;
        this.sotietnghi = sotietnghi;
    }
    @EmbeddedId
    private CourseGradeId courseGradeId;

    private Double hs1;

    private Double hs2;

    private Double hs3;

    private Double hs4;

    private Double hs5;

    private Long sotietnghi;

    private Double finaltest;
}
