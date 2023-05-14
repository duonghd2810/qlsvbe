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
    private String id_classroom;
    private String id_day;
    private String lesson;
    private String teacherName;
    private String status;

    public CourseRegistedByStudent(Long id, String subjectCode, String tenHp,
                                   Integer soTc, String id_classroom, String id_day,
                                   String lesson, String teacherName,String status){
        super(id,tenHp,soTc);
        this.subjectCode = subjectCode;
        this.id_classroom = id_classroom;
        this.id_day = id_day;
        this.lesson = lesson;
        this.teacherName = teacherName;
        this.status = status;
    }
}
