package com.example.doan.services;

import com.example.doan.dtos.MajorDTO;
import com.example.doan.models.Major;

import java.util.List;

public interface IMajorService {
    Major getMajorById(Long id);
    Major createMajor(MajorDTO majorDTO);
    List<Major> getListMajor();
    Major updateMajor(MajorDTO majorDTO, Long id);
    String deleteMajor(Long id);
    Major addStudentToMajor(Long idMajor, Long idStudent);
    String deleteStudentFromMajor(Long idMajor, Long idStudent);
}
