package com.example.doan.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassSectionUpdDTO {
    private Long id_teacher;
    private String id_day;
    private String lesson;
    private String id_classroom;
}
