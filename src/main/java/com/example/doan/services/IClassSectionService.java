package com.example.doan.services;

import com.example.doan.dtos.ClassSectionDTO;
import com.example.doan.dtos.ClassSectionUpdDTO;
import com.example.doan.mapper.TKBInfoMapper;
import com.example.doan.models.ClassSection;

import java.util.List;

public interface IClassSectionService {
    ClassSection createClassSection(Long idsubject);
    List<ClassSectionDTO> getAllClassSection();
    List<ClassSectionDTO> getListClassSectionByStudent(Long idStudent);
    ClassSection updateClassSection(Long idClassSection, ClassSectionUpdDTO classSectionUpdDTO);
    String deleteClassSection(Long idClassSection);
    List<ClassSectionDTO> getListByTeacher(Long idTeacher);
    ClassSectionDTO getClassSectionById(Long idClass);
    List<TKBInfoMapper> getTKBByTeacher(Long idTeacher);
}
