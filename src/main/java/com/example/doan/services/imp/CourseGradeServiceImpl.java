package com.example.doan.services.imp;

import com.example.doan.dtos.CourseRegistedByStudent;
import com.example.doan.dtos.ReponseStudentByClassSection;
import com.example.doan.dtos.ResponseCourseForStudent;
import com.example.doan.exceptions.DuplicateException;
import com.example.doan.exceptions.NotFoundException;
import com.example.doan.mapper.ResponseStudentMapper;
import com.example.doan.mapper.ResponseTKBMapper;
import com.example.doan.mapper.StudentInfoMapper;
import com.example.doan.mapper.TKBInfoMapper;
import com.example.doan.models.ClassSection;
import com.example.doan.models.CourseGrade;
import com.example.doan.models.CourseGradeId;
import com.example.doan.models.User;
import com.example.doan.repositories.ClassSectionRepository;
import com.example.doan.repositories.CourseGradeRepository;
import com.example.doan.repositories.UserRepository;
import com.example.doan.services.ICourseGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseGradeServiceImpl implements ICourseGradeService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
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
            ResponseCourseForStudent responseCourse = new ResponseCourseForStudent(classSection.getId(), classSection.getSubjectt().getSubjectName(),
                    classSection.getSubjectt().getTc(), courseGrade.getHs1(), courseGrade.getHs2(),
                    courseGrade.getHs3(), courseGrade.getHs4(), courseGrade.getHs5(),courseGrade.getFinaltest());
            courseForStudents.add(responseCourse);
        }
        return courseForStudents;
    }

    @Override
    public List<ReponseStudentByClassSection> getAllStudentForClassSection(Long idClass) {
        List<CourseGrade> courseGrades = courseGradeRepository.getCourseGradeByIdClass(idClass);
        List<ReponseStudentByClassSection> listByIdClass = new ArrayList<>();
        for(CourseGrade courseGrade: courseGrades){
            Optional<User> student = userRepository.findById(courseGrade.getCourseGradeId().getStudentId());
            ReponseStudentByClassSection responseStudent = new ReponseStudentByClassSection(student.get().getUsername(), student.get().getFullName(),
                                                                courseGrade.getHs1(), courseGrade.getHs2(), courseGrade.getHs3(), courseGrade.getHs4(),
                                                                courseGrade.getHs5(), courseGrade.getSotietnghi(),courseGrade.getFinaltest());
            listByIdClass.add(responseStudent);
        }
        return listByIdClass;
    }

    @Override
    public CourseGrade registClassSection(Long idClassSection, Long idStudent) {
        Optional<ClassSection> classSection  = classSectionRepository.findById(idClassSection);
        if(classSection.isEmpty()) {
            throw new NotFoundException("Lớp học phần không tồn tại");
        }
        Optional<User> student = userRepository.findById(idStudent);
        if(student.isEmpty()) {
            throw new NotFoundException("Không có sinh viên này");
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
    public List<CourseRegistedByStudent> listCourseRegistedByStudent(Long idStudent) {
        List<CourseRegistedByStudent> listRegisterByIdClass = new ArrayList<>();
        Optional<User> student = userRepository.findById(idStudent);
        if(student.isEmpty()){
            throw new NotFoundException("Không có sinh viên này");
        }
        List<CourseGrade> courseGradeList = courseGradeRepository.findByStudent(student.get().getId());
        for(CourseGrade courseGrade: courseGradeList){
            ClassSection classSection = classSectionRepository.getClassSectionDetail(courseGrade.getCourseGradeId().getClassSectionId());
            if(classSection != null){
                CourseRegistedByStudent courseRegisted = new CourseRegistedByStudent(classSection.getId(),classSection.getSubjectt().getSubjectCode(),
                                                            classSection.getSubjectt().getSubjectName(),classSection.getSubjectt().getTc(),
                                                            classSection.getClassroom().getTenPhong(),classSection.getDayOfWeek().getDayOfWeek(),
                                                            classSection.getLesson(), classSection.getTeacher().getFullName());
                listRegisterByIdClass.add(courseRegisted);
            }
        }
        return listRegisterByIdClass;
    }

    @Override
    public List<TKBInfoMapper> listTKBByStudent(Long idStudent) {
        Optional<User> user = userRepository.findById(idStudent);
        if(user.isEmpty()){
            throw new NotFoundException("Không có sinh viên này");
        }
        String sql = "select cs.id as class_section_id, s.subject_code,s.subject_name, cs.id_classroom, cs.id_day, cs.lesson, cs.id_teacher, tea.full_name as teacherName " +
                "from course_grade cg join users u on cg.student_id = u.id " +
                "join class_section cs on cg.class_section_id = cs.id " +
                "join subjects s on s.id = cs.id_subject " +
                "join users tea on tea.id = cs.id_teacher " +
                "where cg.student_id = ? " +
                "order by cs.id_day, cs.lesson";
        List<TKBInfoMapper> listTKBByStudent = jdbcTemplate.query(sql,new ResponseTKBMapper(),idStudent);
        return listTKBByStudent;
    }

    @Override
    public String deleteCourseGradeRegisted(Long idStudent,Long idClassSection) {
        Optional<ClassSection> classSection  = classSectionRepository.findById(idClassSection);
        if(classSection.isEmpty()) {
            throw new NotFoundException("Lớp học phần không tồn tại");
        }
        Optional<User> student = userRepository.findById(idStudent);
        if(student.isEmpty()) {
            throw new NotFoundException("Không có sinh viên này");
        }
        courseGradeRepository.deleteClassSectionRegisted(idClassSection,idStudent);
        return "Hủy đăng ký thành công";
    }

    @Override
    public void savePointForStudentToDb(MultipartFile file, Long idClass){
        if(ReportService.isValidExcelFile(file)){
            try {
                List<ReponseStudentByClassSection> students = ReportService.getStudentDataFromExcel(file.getInputStream());
                for(ReponseStudentByClassSection student: students){
                    User user = userRepository.findByUsername(student.getMasv());
                    CourseGrade oldCourseGrade = courseGradeRepository.findCourseGradeByCourseGradeId(new CourseGradeId(user.getId(),idClass));
                    CourseGrade courseGrade = new CourseGrade(student.getHs1(), student.getHs2(),
                                        student.getHs3(),student.getHs4(),student.getHs5(),student.getSotietnghi());
                    courseGrade.setCourseGradeId(oldCourseGrade.getCourseGradeId());
                    courseGradeRepository.save(courseGrade);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveFinalPointForStudent(MultipartFile file) {
        if(ReportService.isValidExcelFile(file)){
            try {
                List<ReponseStudentByClassSection> studentImports = ReportService.importFinalPoint(file.getInputStream());
                String subjectCode = studentImports.get(0).getSubjectCode();
                String sql  = "select s.subject_code, cg.student_id, cg.finaltest from subjects s join class_section cs on s.id = cs.id_subject " +
                        "join course_grade cg on cs.id = cg.class_section_id " +
                        "where s.subject_code = ?";
                List<StudentInfoMapper> lists = jdbcTemplate.query(sql,new ResponseStudentMapper(),subjectCode);
                for(ReponseStudentByClassSection student: studentImports){
                    User user = userRepository.findByUsername(student.getMasv());
                    for(StudentInfoMapper info : lists){
                        if(user.getId() == info.getStudent_id()){
                            String sqlUp = "update course_grade set finaltest = ? " +
                                    "from class_section cs join course_grade cg on cs.id = cg.class_section_id " +
                                    "join subjects s on cs.id_subject = s.id " +
                                    "where subject_code = ? and cg.student_id = ?";
                            jdbcTemplate.update(sqlUp,student.getFinaltest(), subjectCode, user.getId());
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
