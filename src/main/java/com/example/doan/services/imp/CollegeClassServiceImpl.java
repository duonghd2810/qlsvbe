package com.example.doan.services.imp;

import com.example.doan.dtos.ClassMajorDTO;
import com.example.doan.dtos.CollegeClassDTO;
import com.example.doan.exceptions.NotFoundException;
import com.example.doan.models.CollegeClass;
import com.example.doan.models.Major;
import com.example.doan.models.User;
import com.example.doan.repositories.CollegeClassRepository;
import com.example.doan.repositories.MajorRepository;
import com.example.doan.repositories.UserRepository;
import com.example.doan.services.ICollegeClassService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CollegeClassServiceImpl implements ICollegeClassService {
    @Autowired
    private MajorRepository majorRepository;
    @Autowired
    private CollegeClassRepository collegeClassRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ClassMajorDTO> getAllClass() {
        List<CollegeClass> list = collegeClassRepository.findAll();
        List<ClassMajorDTO> listResult = new ArrayList<>();
        for(CollegeClass item: list){
            ClassMajorDTO classMajorDTO = new ClassMajorDTO(item.getId(), item.getClassName(), item.getHomeroomTeacher(), item.getMajor().getId(), item.getMajor().getMajorName());
            listResult.add(classMajorDTO);
        }
        return listResult;
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
        CollegeClass collegeClass = new CollegeClass(collegeClassDTO.getClassName(),collegeClassDTO.getHomeroomTeacher(),major.get());
        CollegeClass newClass = collegeClassRepository.save(collegeClass);
        return newClass;
    }

    @Override
    public CollegeClass updateClass(CollegeClassDTO collegeClassDTO, Long id) {
        Optional<CollegeClass> collegeClass = collegeClassRepository.findById(id);
        if(collegeClass.isEmpty()){
            throw new NotFoundException("Class is not found");
        }
        CollegeClass newClass = new CollegeClass(collegeClassDTO.getClassName(),collegeClassDTO.getHomeroomTeacher(), majorRepository.findById(collegeClassDTO.getId_major()).get());
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

    @Override
    public CollegeClass addStudentToCollegeClass(Long idCollegeClass, Long idStudent) {
        Optional<CollegeClass> collegeClass = collegeClassRepository.findById(idCollegeClass);
        if(collegeClass.isEmpty()){
            throw new NotFoundException("College class is not found");
        }
        Optional<User> student = userRepository.findById(idStudent);
        if(student.isEmpty()){
            throw new NotFoundException("Student is not found");
        }
        CollegeClass collegeClass1 = this.modelMapper.map(collegeClass.get(),CollegeClass.class);
        student.get().setCollegeClass(collegeClass.get());
        userRepository.save(student.get());
        collegeClass1.setStudentlist(List.of(student.get()));
        return collegeClass1;
    }

    @Override
    public String deleteStudent(Long idCollegeClass, Long idStudent) {
        Optional<CollegeClass> collegeClass = collegeClassRepository.findById(idCollegeClass);
        if(collegeClass.isEmpty()){
            throw new NotFoundException("College class is not found");
        }
        Optional<User> student = userRepository.findById(idStudent);
        if(student.isEmpty()){
            throw new NotFoundException("Student is not found");
        }
        student.get().setCollegeClass(null);
        userRepository.save(student.get());
        if(collegeClass.get().getStudentlist().contains(student.get())){
            collegeClass.get().getStudentlist().remove(student.get());
        }
        collegeClassRepository.save(collegeClass.get());
        return "Delete student from college class success";
    }
}
