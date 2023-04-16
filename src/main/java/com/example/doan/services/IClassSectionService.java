package com.example.doan.services;

import com.example.doan.dtos.ClassSectionDTO;
import com.example.doan.models.ClassSection;
import com.example.doan.models.User;

import java.util.List;

public interface IClassSectionService {
    ClassSection createClassSection(Long idsubject);
    List<ClassSectionDTO> getListByIdSubject(Long idSubject);
    ClassSection updateTeacherForClass(Long idClassSection,Long idTeacher);
    String deleteClassSection(Long idClassSection);
    List<ClassSection> getListByTeacher(Long idTeacher);
}
