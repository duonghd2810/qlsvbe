package com.example.doan.services.imp;

import com.example.doan.dtos.CourseGradeDTO;
import com.example.doan.exceptions.DuplicateException;
import com.example.doan.exceptions.NotFoundException;
import com.example.doan.models.ClassSection;
import com.example.doan.models.CourseGrade;
import com.example.doan.models.CourseGradeId;
import com.example.doan.models.User;
import com.example.doan.repositories.ClassSectionRepository;
import com.example.doan.repositories.CourseGradeRepository;
import com.example.doan.repositories.UserRepository;
import com.example.doan.services.ICourseGradeService;
import com.example.doan.utils.ConvertObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseGradeServiceImpl implements ICourseGradeService {
    @Autowired
    private ClassSectionRepository classSectionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseGradeRepository courseGradeRepository;

    @Override
    public CourseGrade registClassSection(Long idClassSection, Long idStudent) {
        Optional<ClassSection> classSection  = classSectionRepository.findById(idClassSection);
        if(classSection.isEmpty()) {
            throw new NotFoundException("Class section is not found");
        }
        Optional<User> student = userRepository.findById(idStudent);
        if(student.isEmpty()){
            throw new NotFoundException("Student is not found");
        }
        CourseGradeId courseGradeId = new CourseGradeId(student.get().getId(),classSection.get().getId());
        CourseGrade courseGrade = new CourseGrade();
        courseGrade.setCourseGradeId(courseGradeId);
        return courseGradeRepository.save(courseGrade);
    }

    @Override
    public CourseGrade enterPoint(Long idClassSection, Long idStudent, CourseGradeDTO courseGradeDTO) {
        Optional<ClassSection> classSection  = classSectionRepository.findById(idClassSection);
        if(classSection.isEmpty()) {
            throw new NotFoundException("Class section is not found");
        }
        Optional<User> student = userRepository.findById(idStudent);
        if(student.isEmpty()){
            throw new NotFoundException("Student is not found");
        }
        CourseGradeId courseGradeId = new CourseGradeId(student.get().getId(),classSection.get().getId());
        CourseGrade courseGrade = courseGradeRepository.findCourseGradeByCourseGradeId(courseGradeId);
        if(courseGrade == null){
            throw new NotFoundException("This student has not exists in this section class");
        }
        ConvertObject.convertCourseGradeDTOToCourseGrade(courseGradeDTO,courseGrade);
        CourseGrade newCourseGrade = courseGradeRepository.save(courseGrade);
        return newCourseGrade;
    }
}
