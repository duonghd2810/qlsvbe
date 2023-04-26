package com.example.doan.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseRegistedByStudent extends ResponseCourseForStudent{
    private String subjectCode;

    public CourseRegistedByStudent(Long id,String subjectCode,String tenHp,Integer soTc){
        super(id,tenHp,soTc);
        this.subjectCode = subjectCode;
    }
}
