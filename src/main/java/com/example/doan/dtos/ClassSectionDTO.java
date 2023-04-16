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
    private String maHp;
    private String tenHp;
    private Long id_teacher;
    private String teacherName;
    private Integer soTc;

    public ClassSectionDTO(Long id, String maHp, String tenHp, Integer soTc) {
        this.id = id;
        this.maHp = maHp;
        this.tenHp = tenHp;
        this.soTc = soTc;
    }
}
