package com.example.doan.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubjectDTO {
    private String subjectName;
    private Integer tc;
    private Long id_teacher;
}
