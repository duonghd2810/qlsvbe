package com.example.doan.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseGradeDTO {
    private Double hs1;
    private Double hs2;
    private Double hs3;
    private Double hs4;
    private Double hs5;
    private Long sotietnghi;
    private Double finaltest;
}
