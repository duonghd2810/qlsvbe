package com.example.doan.services.imp;

import com.example.doan.dtos.ReponseStudentByClassSection;
import com.example.doan.models.CourseGrade;
import com.example.doan.models.User;
import com.example.doan.repositories.CourseGradeRepository;
import com.example.doan.repositories.UserRepository;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    @Autowired
    private CourseGradeRepository courseGradeRepository;
    @Autowired
    private UserRepository userRepository;

    public CellStyle styleHeader(Workbook workbook){
        Font font = workbook.createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeight((short)400);

        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;
    }
    public CellStyle styleContent(Workbook workbook){
        Font font = workbook.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeight((short)400);

        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;
    }
    public ByteArrayInputStream generalExcel(Long idClass) throws IOException {
        ByteArrayOutputStream outData = new ByteArrayOutputStream();
        HSSFWorkbook workbook = new HSSFWorkbook();
        try{
            List<CourseGrade> courseGrades = courseGradeRepository.getCourseGradeByIdClass(idClass);
            List<ReponseStudentByClassSection> listByIdClass = new ArrayList<>();
            for(CourseGrade courseGrade: courseGrades){
                Optional<User> student = userRepository.findById(courseGrade.getCourseGradeId().getStudentId());
                ReponseStudentByClassSection responseStudent = new ReponseStudentByClassSection(student.get().getUsername(), student.get().getFullName(),
                        courseGrade.getHs1(), courseGrade.getHs2(), courseGrade.getHs3(), courseGrade.getHs4(),
                        courseGrade.getHs5(), courseGrade.getSotietnghi(),courseGrade.getFinaltest());
                listByIdClass.add(responseStudent);
            }


            HSSFSheet sheet = workbook.createSheet("Bảng điểm");
            HSSFRow rowTitle = sheet.createRow(0);
            HSSFCell cellTitle = rowTitle.createCell(0);
            cellTitle.setCellStyle(styleHeader(workbook));
            cellTitle.setCellValue("Danh sách điểm");

            HSSFRow rowHead = sheet.createRow(1);

            HSSFCell cellSTT = rowHead.createCell(1);
            cellSTT.setCellStyle(styleHeader(workbook));
            cellSTT.setCellValue("STT");

            HSSFCell cellTen = rowHead.createCell(2);
            cellTen.setCellStyle(styleHeader(workbook));
            cellTen.setCellValue("Họ và tên");

            HSSFCell cellTx1 = rowHead.createCell(3);
            cellTx1.setCellStyle(styleHeader(workbook));
            cellTx1.setCellValue("TX1");

            HSSFCell cellTx2 = rowHead.createCell(4);
            cellTx2.setCellStyle(styleHeader(workbook));
            cellTx2.setCellValue("TX2");

            HSSFCell cellTx3 = rowHead.createCell(5);
            cellTx3.setCellStyle(styleHeader(workbook));
            cellTx3.setCellValue("TX3");

            HSSFCell cellTx4 = rowHead.createCell(6);
            cellTx4.setCellStyle(styleHeader(workbook));
            cellTx4.setCellValue("TX4");

            HSSFCell cellTx5 = rowHead.createCell(7);
            cellTx5.setCellStyle(styleHeader(workbook));
            cellTx5.setCellValue("TX5");

            HSSFCell cellSotietnghi = rowHead.createCell(8);
            cellSotietnghi.setCellStyle(styleHeader(workbook));
            cellSotietnghi.setCellValue("Số tiết nghỉ");

            int rowStart = 2;
            for(ReponseStudentByClassSection student: listByIdClass){
                HSSFRow dataRow = sheet.createRow(rowStart);

                sheet.autoSizeColumn(1);
                HSSFCell stt = dataRow.createCell(1);
                stt.setCellStyle(styleContent(workbook));
                stt.setCellValue(listByIdClass.indexOf(student));

                sheet.autoSizeColumn(2);
                HSSFCell ten = dataRow.createCell(2);
                ten.setCellStyle(styleContent(workbook));
                ten.setCellValue(student.getTensinhvien());

                sheet.autoSizeColumn(3);
                HSSFCell tx1 = dataRow.createCell(3);
                tx1.setCellStyle(styleContent(workbook));
                tx1.setCellValue(student.getTensinhvien());

                sheet.autoSizeColumn(4);
                HSSFCell tx2 = dataRow.createCell(4);
                tx2.setCellStyle(styleContent(workbook));
                tx2.setCellValue(student.getTensinhvien());

                sheet.autoSizeColumn(5);
                HSSFCell tx3 = dataRow.createCell(5);
                tx3.setCellStyle(styleContent(workbook));
                tx3.setCellValue(student.getTensinhvien());

                sheet.autoSizeColumn(6);
                HSSFCell tx4 = dataRow.createCell(6);
                tx4.setCellStyle(styleContent(workbook));
                tx4.setCellValue(student.getTensinhvien());

                sheet.autoSizeColumn(7);
                HSSFCell tx5 = dataRow.createCell(7);
                tx5.setCellStyle(styleContent(workbook));
                tx5.setCellValue(student.getTensinhvien());

                sheet.autoSizeColumn(8);
                HSSFCell sotietnghi = dataRow.createCell(8);
                sotietnghi.setCellStyle(styleContent(workbook));
                sotietnghi.setCellValue(student.getTensinhvien());

                rowStart++;
            }
            workbook.write(outData);
            return new ByteArrayInputStream(outData.toByteArray());
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException
                    ("Error while generating excel.");
        } finally {
            workbook.close();
            outData.close();
        }
    }
}
