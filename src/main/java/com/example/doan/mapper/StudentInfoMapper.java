package com.example.doan.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentInfoMapper {
    private String subjectCode;
    private Long student_id;
    private Double finaltest;

    public StudentInfoMapper(String subjectCode, Long student_id) {
        this.subjectCode = subjectCode;
        this.student_id = student_id;
    }
}
