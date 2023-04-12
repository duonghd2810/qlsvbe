package com.example.doan.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassMajorDTO {
    private Long id;

    private String className;

    private String homeroomTeacher;

    private Long id_major;

    private String majorName;
}
