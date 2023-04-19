package com.example.doan.services.imp;

import com.example.doan.dtos.CourseGradeDTO;
import com.example.doan.dtos.ResponseCourseForStudent;
import com.example.doan.dtos.ResponseSubjectDTO;
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

import java.util.ArrayList;
import java.util.List;
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
    public List<ResponseCourseForStudent> getAllCourseForStudent(Long idStudent) {
        List<CourseGrade> courseGrades = courseGradeRepository.findByStudent(idStudent);
        List<ResponseCourseForStudent> courseForStudents = new ArrayList<>();
        for(CourseGrade courseGrade: courseGrades){
            ClassSection classSection = classSectionRepository.getClassSectionById(courseGrade.getCourseGradeId().getClassSectionId());
            ResponseCourseForStudent responseCourse = new ResponseCourseForStudent(classSection.getId(), classSection.getSubjectt().getSubjectName(), classSection.getSubjectt().getTc(),
                                                                        courseGrade.getHs1(), courseGrade.getHs2(), courseGrade.getFinaltest());
            courseForStudents.add(responseCourse);
        }
        return courseForStudents;
    }

    @Override
    public CourseGrade registClassSection(Long idClassSection, Long idStudent) {
        Optional<ClassSection> classSection  = classSectionRepository.findById(idClassSection);
        if(classSection.isEmpty()) {
            throw new NotFoundException("Class section is not found");
        }
        Optional<User> student = userRepository.findById(idStudent);
        if(student.isEmpty()) {
            throw new NotFoundException("Student is not found");
        }
        List<CourseGrade> courseGradeList = courseGradeRepository.findByStudent(student.get().getId());
        if(courseGradeList.size() != 0){
            for(CourseGrade courseGrade:courseGradeList){
                Optional<ClassSection> classSection1 = classSectionRepository.findById(courseGrade.getCourseGradeId().getClassSectionId());
                if(classSection.get().getSubjectt().getId() == classSection1.get().getSubjectt().getId()){
                    throw new DuplicateException("Bạn đã đăng ký học phần này rồi");
                }
            }
        }
        CourseGradeId courseGradeId = new CourseGradeId(student.get().getId(),classSection.get().getId());
        CourseGrade courseGrade = new CourseGrade();
        courseGrade.setCourseGradeId(courseGradeId);
        CourseGrade newCourseGrade = courseGradeRepository.save(courseGrade);
        return newCourseGrade;
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
