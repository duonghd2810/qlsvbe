package com.example.doan.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReponseStudentByClassSection {
    private String subjectCode;
    private String masv;
    private String tensinhvien;
    private Double hs1;
    private Double hs2;
    private Double hs3;
    private Double hs4;
    private Double hs5;
    private Long sotietnghi;
    private Double finaltest;


    public ReponseStudentByClassSection(String subjectCode, String masv, String tensinhvien, Double finaltest) {
        this.subjectCode = subjectCode;
        this.masv = masv;
        this.tensinhvien = tensinhvien;
        this.finaltest = finaltest;
    }

    public ReponseStudentByClassSection(String masv, String tensinhvien, Double hs1, Double hs2,
                                        Double hs3, Double hs4, Double hs5, Long sotietnghi, Double finaltest) {
        this.masv = masv;
        this.tensinhvien = tensinhvien;
        this.hs1 = hs1;
        this.hs2 = hs2;
        this.hs3 = hs3;
        this.hs4 = hs4;
        this.hs5 = hs5;
        this.sotietnghi = sotietnghi;
        this.finaltest = finaltest;
    }
}
