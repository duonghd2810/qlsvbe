package com.example.doan.services.imp;

import com.example.doan.dtos.ResponseSubjectDTO;
import com.example.doan.dtos.SubjectDTO;
import com.example.doan.exceptions.NotFoundException;
import com.example.doan.models.Major;
import com.example.doan.models.Subject;
import com.example.doan.repositories.MajorRepository;
import com.example.doan.repositories.SubjectRepository;
import com.example.doan.services.ISubjectService;
import com.example.doan.utils.GenaralDataUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements ISubjectService {
    @Autowired
    private MajorRepository majorRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Override
    public List<ResponseSubjectDTO> getAllSubject() {
        List<Subject> subjects = subjectRepository.getAllSubject();
        List<ResponseSubjectDTO> responseSubjectDTOS = new ArrayList<>();
        for(Subject subject: subjects){
            ResponseSubjectDTO responseSubjectDTO = new ResponseSubjectDTO(subject.getId(),subject.getSubjectCode(),subject.getSubjectName(), subject.getPrice(),
                                                            subject.getTc(),subject.getMajorSubject().getId(),subject.getMajorSubject().getMajorName());
            responseSubjectDTOS.add(responseSubjectDTO);
        }
        return responseSubjectDTOS;
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
        Optional<Major> major = majorRepository.findById(subjectDTO.getId_major());
        if(major.isEmpty()){
            throw new NotFoundException("Major is not found");
        }
        Subject oldSubject = new Subject();
        String code = GenaralDataUser.generateSubjecCode();
        do{
            oldSubject = subjectRepository.findBySubjectCode(code);
            if(oldSubject != null){
                code = GenaralDataUser.generateSubjecCode();
            }
        }while(oldSubject != null);
        Subject subject = new Subject(major.get(),code,subjectDTO.getSubjectName(), subjectDTO.getTc());
        Subject newSubject = subjectRepository.save(subject);
        return newSubject;
    }

    @Override
    public Subject updateSubject(SubjectDTO subjectDTO, Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if(subject.isEmpty()){
            throw new NotFoundException("Subject is not found");
        }
        Subject newSubject = new Subject(majorRepository.findById(subjectDTO.getId_major()).get(),subjectDTO.getSubjectName(), subjectDTO.getTc());
        newSubject.setSubjectCode(subject.get().getSubjectCode());
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
