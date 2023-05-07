package com.example.doan.services.imp;

import com.example.doan.dtos.ClassSectionDTO;
import com.example.doan.dtos.ClassSectionUpdDTO;
import com.example.doan.exceptions.NotFoundException;
import com.example.doan.mapper.ResponseTKBMapper;
import com.example.doan.mapper.TKBInfoMapper;
import com.example.doan.models.ClassSection;
import com.example.doan.models.Subject;
import com.example.doan.models.User;
import com.example.doan.repositories.*;
import com.example.doan.services.IClassSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
    @Autowired
    private ClassRoomRepository classRoomRepository;
    @Autowired
    private DayOfWeekRepository dayOfWeekRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

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
                    item.getSubjectt().getSubjectName(),item.getSubjectt().getTc(),item.getSubjectt().getMajorSubject().getId());
            if(item.getTeacher() != null){
                classSectionDTO.setId_teacher(item.getTeacher().getId());
                classSectionDTO.setTeacherName(item.getTeacher().getFullName());
            }
            if(item.getClassroom() != null){
                classSectionDTO.setId_classroom(item.getClassroom().getTenPhong());
            }
            if(item.getDayOfWeek() != null){
                classSectionDTO.setId_day(item.getDayOfWeek().getDayOfWeek());
            }
            if(item.getLesson() != null){
                classSectionDTO.setLesson(item.getLesson());
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
                    item.getSubjectt().getSubjectName(),item.getSubjectt().getTc(), item.getSubjectt().getMajorSubject().getId());
            if(item.getTeacher() != null){
                classSectionDTO.setId_teacher(item.getTeacher().getId());
                classSectionDTO.setTeacherName(item.getTeacher().getFullName());
            }
            if(item.getClassroom() != null){
                classSectionDTO.setId_classroom(item.getClassroom().getTenPhong());
            }
            if(item.getDayOfWeek() != null){
                classSectionDTO.setId_day(item.getDayOfWeek().getDayOfWeek());
            }
            if(item.getLesson() != null){
                classSectionDTO.setLesson(item.getLesson());
            }
            classSectionDTOList.add(classSectionDTO);
        }
        return classSectionDTOList;
    }

    @Override
    public ClassSection updateClassSection(Long idClassSection, ClassSectionUpdDTO classSectionUpdDTO) {
        Optional<ClassSection> classSection = classSectionRepository.findById(idClassSection);
        if(classSection.isEmpty()) {
            throw new NotFoundException("Lớp học phần không tồn tại");
        }
        classSection.get().setLesson(classSectionUpdDTO.getLesson());
        classSection.get().setTeacher(userRepository.findById(classSectionUpdDTO.getId_teacher()).get());
        classSection.get().setClassroom(classRoomRepository.findById(classSectionUpdDTO.getId_classroom()).get());
        classSection.get().setDayOfWeek(dayOfWeekRepository.findById(classSectionUpdDTO.getId_day()).get());
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
                    item.getSubjectt().getSubjectName(),item.getSubjectt().getTc(),item.getSubjectt().getMajorSubject().getId());
            if(item.getTeacher() != null){
                classSectionDTO.setId_teacher(item.getTeacher().getId());
                classSectionDTO.setTeacherName(item.getTeacher().getFullName());
            }
            if(item.getClassroom() != null){
                classSectionDTO.setId_classroom(item.getClassroom().getTenPhong());
            }
            if(item.getDayOfWeek() != null){
                classSectionDTO.setId_day(item.getDayOfWeek().getDayOfWeek());
            }
            if(item.getLesson() != null){
                classSectionDTO.setLesson(item.getLesson());
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
                classSection.getSubjectt().getSubjectName(), classSection.getSubjectt().getTc(),classSection.getSubjectt().getMajorSubject().getId());
        if(classSection.getClassroom() != null){
            classSectionDTO.setId_classroom(classSection.getClassroom().getTenPhong());
        }
        if(classSection.getDayOfWeek() != null){
            classSectionDTO.setId_day(classSection.getDayOfWeek().getDayOfWeek());
        }
        if(classSection.getLesson() != null){
            classSectionDTO.setLesson(classSection.getLesson());
        }
        return classSectionDTO;
    }

    @Override
    public List<TKBInfoMapper> getTKBByTeacher(Long idTeacher) {
        Optional<User> user = userRepository.findById(idTeacher);
        if(user.isEmpty()){
            throw new NotFoundException("Giao vien khong ton tai");
        }
        String sql = "select cs.id as class_section_id, cs.id_teacher, cs.id_day, cs.id_classroom, cs.lesson, cs.id_subject, " +
                "s.subject_code, s.subject_name, tea.full_name as teacherName " +
                "from class_section cs join subjects s on s.id = cs.id_subject " +
                "join users tea on cs.id_teacher = tea.id " +
                "where cs.id_teacher = ? " +
                "order by cs.id_day, cs.lesson";
        List<TKBInfoMapper> listTKB = jdbcTemplate.query(sql,new ResponseTKBMapper(),idTeacher);
        return listTKB;
    }
}
