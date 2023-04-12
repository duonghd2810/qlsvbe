package com.example.doan.services.imp;

import com.example.doan.dtos.CollegeClassDTO;
import com.example.doan.exceptions.NotFoundException;
import com.example.doan.models.CollegeClass;
import com.example.doan.models.Major;
import com.example.doan.repositories.CollegeClassRepository;
import com.example.doan.repositories.MajorRepository;
import com.example.doan.services.ICollegeClassService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollegeClassServiceImpl implements ICollegeClassService {
    @Autowired
    private MajorRepository majorRepository;
    @Autowired
    private CollegeClassRepository collegeClassRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CollegeClass> getAllClass() {
        return collegeClassRepository.findAll();
    }

    @Override
    public CollegeClass getClassById(Long id) {
        Optional<CollegeClass> collegeClass = collegeClassRepository.findById(id);
        if(collegeClass.isEmpty()){
            throw new NotFoundException("Class is not found");
        }
        return collegeClass.get();
    }

    @Override
    public CollegeClass createClass(CollegeClassDTO collegeClassDTO) {
        Optional<Major> major = majorRepository.findById(collegeClassDTO.getId_major());
        if(major.isEmpty()){
            throw new NotFoundException("Major is not found");
        }
        CollegeClass collegeClass = new CollegeClass(collegeClassDTO.getClassName(),collegeClassDTO.getHomeroomTeacher(),collegeClassDTO.getSiso(),major.get());
        CollegeClass newClass = collegeClassRepository.save(collegeClass);
        return newClass;
    }

    @Override
    public CollegeClass updateClass(CollegeClassDTO collegeClassDTO, Long id) {
        Optional<CollegeClass> collegeClass = collegeClassRepository.findById(id);
        if(collegeClass.isEmpty()){
            throw new NotFoundException("Class is not found");
        }
        CollegeClass newClass = new CollegeClass(collegeClassDTO.getClassName(),collegeClassDTO.getHomeroomTeacher(), collegeClassDTO.getSiso(), majorRepository.findById(collegeClassDTO.getId_major()).get());
        newClass.setId(collegeClass.get().getId());
        return collegeClassRepository.save(newClass);
    }

    @Override
    public String deleteClass(Long id) {
        Optional<CollegeClass> collegeClass = collegeClassRepository.findById(id);
        if(collegeClass.isEmpty()){
            throw new NotFoundException("Class is not found");
        }
        collegeClassRepository.delete(collegeClass.get());
        return "Delete success";
    }
}
