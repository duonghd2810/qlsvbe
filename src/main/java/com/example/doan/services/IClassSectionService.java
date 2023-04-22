package com.example.doan.services;

import com.example.doan.dtos.ClassSectionDTO;
import com.example.doan.models.ClassSection;

import java.util.List;

public interface IClassSectionService {
    ClassSection createClassSection(Long idsubject);
    List<ClassSectionDTO> getAllClassSection();
    List<ClassSectionDTO> getListClassSectionByStudent(Long idStudent);
    ClassSection updateTeacherForClass(Long idClassSection,Long idTeacher);
    String deleteClassSection(Long idClassSection);
    List<ClassSectionDTO> getListByTeacher(Long idTeacher);
    ClassSectionDTO getClassSectionById(Long idClass);
}
