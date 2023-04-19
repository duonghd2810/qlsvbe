package com.example.doan.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseSubjectDTO {
    private Long id;
    private String subjectCode;
    private String subjectName;
    private Double price;
    private Integer tc;

    private Long id_major;
    private String majorName;
}
