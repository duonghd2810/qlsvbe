package com.example.doan.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassSectionDTO {
    private Long id;
    private String subjectCode;
    private String tenHp;
    private Long id_teacher;
    private String teacherName;
    private Integer soTc;
    private Long idMajor;
    private String id_day;
    private String id_classroom;
    private String lesson;
    private String status;

    public ClassSectionDTO(Long id, String maHp, String tenHp, Integer soTc,Long idMajor, String status) {
        this.id = id;
        this.subjectCode = maHp;
        this.tenHp = tenHp;
        this.soTc = soTc;
        this.idMajor = idMajor;
        this.status = status;
    }
}
