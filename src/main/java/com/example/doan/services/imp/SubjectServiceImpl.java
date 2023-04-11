package com.example.doan.services.imp;

import com.example.doan.dtos.SubjectDTO;
import com.example.doan.exceptions.NotFoundException;
import com.example.doan.models.Subject;
import com.example.doan.models.User;
import com.example.doan.repositories.SubjectRepository;
import com.example.doan.repositories.UserRepository;
import com.example.doan.services.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements ISubjectService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Override
    public List<Subject> getAllSubject() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectById(Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if(subject.isEmpty()){
            throw new NotFoundException("Subject is not found");
        }
        return subject.get();
    }

    @Override
    public Subject createSubject(SubjectDTO subjectDTO) {
        Optional<User> teacher = userRepository.findById(subjectDTO.getId_teacher());
        Subject subject = new Subject(subjectDTO.getSubjectName(), subjectDTO.getTc());
        subject.setListTeacher(List.of(teacher.get()));
        Subject newSubject = subjectRepository.save(subject);
        List<Subject> subjectList = teacher.get().getSubjects();
        subjectList.add(newSubject);
        teacher.get().setSubjects(subjectList);
        userRepository.save(teacher.get());
        return newSubject;
    }

    @Override
    public Subject updateSubject(SubjectDTO subjectDTO, Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if(subject.isEmpty()){
            throw new NotFoundException("Subject is not found");
        }
        Subject newSubject = new Subject(subjectDTO.getSubjectName(), subjectDTO.getTc());
        newSubject.setId(subject.get().getId());
        return newSubject;
    }

    @Override
    public String deleteSubject(Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if(subject.isEmpty()){
            throw new NotFoundException("Subject is not found");
        }
        subjectRepository.delete(subject.get());
        return "Delete success";
    }
}
