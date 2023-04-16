package com.example.doan.services.imp;

import com.example.doan.dtos.SubjectDTO;
import com.example.doan.exceptions.NotFoundException;
import com.example.doan.models.Subject;
import com.example.doan.models.User;
import com.example.doan.repositories.SubjectRepository;
import com.example.doan.repositories.UserRepository;
import com.example.doan.services.ISubjectService;
import com.example.doan.utils.GenaralDataUser;
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
        Subject oldSubject = new Subject();
        String code = GenaralDataUser.generateSubjecCode();
        do{
            oldSubject = subjectRepository.findBySubjectCode(code);
            if(oldSubject != null){
                code = GenaralDataUser.generateSubjecCode();
            }
        }while(oldSubject != null);
        Subject subject = new Subject(code,subjectDTO.getSubjectName(), subjectDTO.getTc());
        Subject newSubject = subjectRepository.save(subject);
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
        return subjectRepository.save(newSubject);
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
