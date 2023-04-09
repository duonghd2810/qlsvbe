package com.example.doan.services;

import com.example.doan.dtos.CollegeClassDTO;
import com.example.doan.models.CollegeClass;

import java.util.List;

public interface ICollegeClassService {
    List<CollegeClass> getAllClass();
    CollegeClass getClassById(Long id);
    CollegeClass createClass(CollegeClassDTO collegeClassDTO);
    CollegeClass updateClass(CollegeClassDTO collegeClassDTO,Long id);
    String deleteClass(Long id);
}
