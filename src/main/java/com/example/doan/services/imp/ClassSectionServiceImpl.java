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
            throw new NotFoundException("Môn học này không tồn tại");
        }
        ClassSection classSection = new ClassSection();
        classSection.setSubjectt(subject.get());
        ClassSection newClassSection =  classSectionRepository.save(classSection);
        return newClassSection;
    }

    @Override
    public List<ClassSectionDTO> getAllClassSection() {
        List<ClassSection> classSections = classSectionRepository.getListClassSection();
        List<ClassSectionDTO> classSectionDTOList = new ArrayList<>();
        for(ClassSection item:classSections){
            ClassSectionDTO classSectionDTO = new ClassSectionDTO(item.getId(),item.getSubjectt().getSubjectCode(),
                    item.getSubjectt().getSubjectName(),item.getSubjectt().getTc());
            if(item.getTeacher() != null){
                classSectionDTO.setId_teacher(item.getTeacher().getId());
                classSectionDTO.setTeacherName(item.getTeacher().getFullName());
            }
            classSectionDTOList.add(classSectionDTO);
        }
        return classSectionDTOList;
    }

    @Override
    public List<ClassSectionDTO> getListClassSectionByStudent(Long idStudent) {
        Optional<User> student = userRepository.findById(idStudent);
        if(student.isEmpty()){
            throw new NotFoundException("Không tồn tại sinh viên này");
        }
        List<ClassSection> classSections = classSectionRepository.getListClassSectionByStudent(idStudent);
        List<ClassSectionDTO> classSectionDTOList = new ArrayList<>();
        for(ClassSection item : classSections){
            ClassSectionDTO classSectionDTO = new ClassSectionDTO(item.getId(),item.getSubjectt().getSubjectCode(),
                    item.getSubjectt().getSubjectName(),item.getSubjectt().getTc());
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
            throw new NotFoundException("Lớp học phần không tồn tại");
        }
        Optional<User> teacher = userRepository.findById(idTeacher);
        if(teacher.isEmpty()){
            throw new NotFoundException("Giáo viên không tồn tại");
        }
        classSection.get().setTeacher(teacher.get());
        return classSectionRepository.save(classSection.get());
    }

    @Override
    public String deleteClassSection(Long idClassSection) {
        Optional<ClassSection> classSection = classSectionRepository.findById(idClassSection);
        if(classSection.isEmpty()){
            throw new NotFoundException("Lớp học phần không tồn tại");
        }
        classSectionRepository.delete(classSection.get());
        return "Delete success";
    }

    @Override
    public List<ClassSectionDTO> getListByTeacher(Long idTeacher) {
        Optional<User> teacher = userRepository.findById(idTeacher);
        if(teacher.isEmpty()){
            throw new NotFoundException("Giáo viên không tồn tại");
        }
        List<ClassSection> classSections = classSectionRepository.getAllClassByTeacher(idTeacher);
        List<ClassSectionDTO> classSectionDTOList = new ArrayList<>();
        for(ClassSection item : classSections){
            ClassSectionDTO classSectionDTO = new ClassSectionDTO(item.getId(),item.getSubjectt().getSubjectCode(),
                    item.getSubjectt().getSubjectName(),item.getSubjectt().getTc());
            if(item.getTeacher() != null){
                classSectionDTO.setId_teacher(item.getTeacher().getId());
                classSectionDTO.setTeacherName(item.getTeacher().getFullName());
            }
            classSectionDTOList.add(classSectionDTO);
        }
        return classSectionDTOList;
    }

    @Override
    public ClassSectionDTO getClassSectionById(Long idClass) {
        ClassSection classSection = classSectionRepository.getClassSectionById(idClass);
        if(classSection == null){
            throw new NotFoundException("Lớp học phần không tồn tại");
        }
        ClassSectionDTO classSectionDTO = new ClassSectionDTO(classSection.getId(), classSection.getSubjectt().getSubjectCode(),
                classSection.getSubjectt().getSubjectName(), classSection.getSubjectt().getTc());
        return classSectionDTO;
    }
}
