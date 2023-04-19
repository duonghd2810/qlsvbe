package com.example.doan.services;

import com.example.doan.dtos.ResponseSubjectDTO;
import com.example.doan.dtos.SubjectDTO;
import com.example.doan.models.Subject;

import java.util.List;

public interface ISubjectService {
    List<ResponseSubjectDTO> getAllSubject();
    Subject getSubjectById(Long id);

    Subject createSubject(SubjectDTO subjectDto);

    Subject updateSubject(SubjectDTO subjectDTO,Long id);

    String deleteSubject(Long id);
}
