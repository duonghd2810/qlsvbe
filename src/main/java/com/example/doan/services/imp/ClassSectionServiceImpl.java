package com.example.doan.services.imp;

import com.example.doan.dtos.ClassSectionDTO;
import com.example.doan.exceptions.NotFoundException;
import com.example.doan.models.ClassSection;
import com.example.doan.models.Subject;
import com.example.doan.models.User;
import com.example.doan.repositories.ClassSectionRepository;
import com.example.doan.repositories.SubjectRepository;
import com.example.doan.repositories.UserRepository;
import com.example.doan.services.IClassSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassSectionServiceImpl implements IClassSectionService {
    public static int MA  = 1;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private ClassSectionRepository classSectionRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public ClassSection createClassSection(Long idsubject) {
        Optional<Subject> subject = subjectRepository.findById(idsubject);
        if(subject.isEmpty()){
            throw new NotFoundException("Subject is not found");
        }
        ClassSection classSection = new ClassSection();
        classSection.setSubjectt(subject.get());
        String mahp = subject.get().getSubjectCode() + "." + MA;
        classSection.setMaHp(mahp);
        ClassSection newClassSection =  classSectionRepository.save(classSection);
        MA++;
        return newClassSection;
    }

    @Override
    public List<ClassSectionDTO> getListByIdSubject(Long idSubject) {
        Optional<Subject> subject = subjectRepository.findById(idSubject);
        if(subject.isEmpty()){
            throw new NotFoundException("Subject is not found");
        }
        List<ClassSection> classSections = classSectionRepository.getListClassByIdSubject(idSubject);
        List<ClassSectionDTO> classSectionDTOList = new ArrayList<>();
        for(ClassSection item:classSections){
            ClassSectionDTO classSectionDTO = new ClassSectionDTO(item.getId(),item.getMaHp(),item.getSubjectt().getSubjectName(),item.getSubjectt().getTc());
            if(item.getTeacher() != null){
                classSectionDTO.setId_teacher(item.getTeacher().getId());
                classSectionDTO.setTeacherName(item.getTeacher().getFullName());
            }
            classSectionDTOList.add(classSectionDTO);
        }
        return classSectionDTOList;
    }

    @Override
    public ClassSection updateTeacherForClass(Long idClassSection,Long idTeacher) {
        Optional<ClassSection> classSection = classSectionRepository.findById(idClassSection);
        if(classSection.isEmpty()){
            throw new NotFoundException("Section class is not found");
        }
        Optional<User> teacher = userRepository.findById(idTeacher);
        if(teacher.isEmpty()){
            throw new NotFoundException("Teacher is not found");
        }
        classSection.get().setTeacher(teacher.get());
        return classSectionRepository.save(classSection.get());
    }

    @Override
    public String deleteClassSection(Long idClassSection) {
        Optional<ClassSection> classSection = classSectionRepository.findById(idClassSection);
        if(classSection.isEmpty()){
            throw new NotFoundException("Section class is not found");
        }
        classSectionRepository.delete(classSection.get());
        return "Delete success";
    }

    @Override
    public List<ClassSection> getListByTeacher(Long idTeacher) {
        Optional<User> teacher = userRepository.findById(idTeacher);
        if(teacher.isEmpty()){
            throw new NotFoundException("Teacher is not found");
        }
        return classSectionRepository.getAllByTeacher(teacher.get());
    }
}