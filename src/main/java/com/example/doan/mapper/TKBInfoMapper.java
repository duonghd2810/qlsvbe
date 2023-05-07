package com.example.doan.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TKBInfoMapper {
    private Long idClassSection;
    private String mahp;
    private String tenhp;
    private Long id_teacher;
    private String teacherName;
    private String id_day;
    private String lesson;
    private String id_classroom;
}
