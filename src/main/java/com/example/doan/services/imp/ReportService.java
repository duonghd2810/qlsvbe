package com.example.doan.services.imp;

import com.example.doan.dtos.ReponseStudentByClassSection;
import com.example.doan.models.CourseGrade;
import com.example.doan.models.User;
import com.example.doan.repositories.CourseGradeRepository;
import com.example.doan.repositories.UserRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class ReportService {
    @Autowired
    private CourseGradeRepository courseGradeRepository;
    @Autowired
    private UserRepository userRepository;

    public static boolean isValidExcelFile(MultipartFile file){
        return Objects.equals(file.getContentType(),"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }
    public CellStyle styleHeader(Workbook workbook){
        Font font = workbook.createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeight((short)280);

        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;
    }
    public CellStyle styleContent(Workbook workbook){
        Font font = workbook.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeight((short)280);

        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;
    }
    public ByteArrayInputStream generalExcel(Long idClass) throws IOException {
        ByteArrayOutputStream outData = new ByteArrayOutputStream();
        Workbook workbook = new XSSFWorkbook();
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

            Sheet sheet = workbook.createSheet("Danh sách sinh viên");

            Row rowHead = sheet.createRow(0);

            Cell cellSTT = rowHead.createCell(0);
            cellSTT.setCellStyle(styleHeader(workbook));
            cellSTT.setCellValue("STT");

            Cell cellMa = rowHead.createCell(1);
            cellMa.setCellStyle(styleHeader(workbook));
            cellMa.setCellValue("Mã sinh viên");

            Cell cellTen = rowHead.createCell(2);
            cellTen.setCellStyle(styleHeader(workbook));
            cellTen.setCellValue("Họ và tên");

            Cell cellTx1 = rowHead.createCell(3);
            cellTx1.setCellStyle(styleHeader(workbook));
            cellTx1.setCellValue("TX1");

            Cell cellTx2 = rowHead.createCell(4);
            cellTx2.setCellStyle(styleHeader(workbook));
            cellTx2.setCellValue("TX2");

            Cell cellTx3 = rowHead.createCell(5);
            cellTx3.setCellStyle(styleHeader(workbook));
            cellTx3.setCellValue("TX3");

            Cell cellTx4 = rowHead.createCell(6);
            cellTx4.setCellStyle(styleHeader(workbook));
            cellTx4.setCellValue("TX4");

            Cell cellTx5 = rowHead.createCell(7);
            cellTx5.setCellStyle(styleHeader(workbook));
            cellTx5.setCellValue("TX5");

            Cell cellSotietnghi = rowHead.createCell(8);
            cellSotietnghi.setCellStyle(styleHeader(workbook));
            cellSotietnghi.setCellValue("Số tiết nghỉ");

            int rowStart = 1;
            for(ReponseStudentByClassSection student: listByIdClass){
                Row dataRow = sheet.createRow(rowStart);

                sheet.autoSizeColumn(0);
                Cell stt = dataRow.createCell(0);
                stt.setCellStyle(styleContent(workbook));
                stt.setCellValue(listByIdClass.indexOf(student)+1);

                sheet.autoSizeColumn(1);
                Cell ma = dataRow.createCell(1);
                ma.setCellStyle(styleContent(workbook));
                ma.setCellValue(student.getMasv());

                sheet.autoSizeColumn(2);
                Cell ten = dataRow.createCell(2);
                ten.setCellStyle(styleContent(workbook));
                ten.setCellValue(student.getTensinhvien());

                sheet.autoSizeColumn(3);
                Cell tx1 = dataRow.createCell(3);
                tx1.setCellStyle(styleContent(workbook));
                if(student.getHs1() != null){
                    tx1.setCellValue(student.getHs1());
                }else tx1.setCellValue("");

                sheet.autoSizeColumn(4);
                Cell tx2 = dataRow.createCell(4);
                tx2.setCellStyle(styleContent(workbook));
                if(student.getHs2() != null){
                    tx2.setCellValue(student.getHs2());
                }else tx2.setCellValue("");

                sheet.autoSizeColumn(5);
                Cell tx3 = dataRow.createCell(5);
                tx3.setCellStyle(styleContent(workbook));
                if(student.getHs3() != null){
                    tx3.setCellValue(student.getHs3());
                }else tx3.setCellValue("");

                sheet.autoSizeColumn(6);
                Cell tx4 = dataRow.createCell(6);
                tx4.setCellStyle(styleContent(workbook));
                if(student.getHs4() != null){
                    tx4.setCellValue(student.getHs4());
                }else tx4.setCellValue("");

                sheet.autoSizeColumn(7);
                Cell tx5 = dataRow.createCell(7);
                tx5.setCellStyle(styleContent(workbook));
                if(student.getHs5() != null){
                    tx5.setCellValue(student.getHs5());
                }else tx5.setCellValue("");

                sheet.autoSizeColumn(8);
                Cell sotietnghi = dataRow.createCell(8);
                sotietnghi.setCellStyle(styleContent(workbook));
                if(student.getSotietnghi() != null){
                    sotietnghi.setCellValue(student.getSotietnghi());
                }else sotietnghi.setCellValue("");


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

    public static List<ReponseStudentByClassSection> getStudentDataFromExcel(InputStream inputStream){
        List<ReponseStudentByClassSection> students = new ArrayList<>();
        try{
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheet("Danh sách sinh viên");
            int rowIndex = 0;
            for(Row row:sheet){
                if(rowIndex == 0){
                    rowIndex ++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                ReponseStudentByClassSection student = new ReponseStudentByClassSection();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    if(cellIndex == 0){
                        cellIndex ++;
                        continue;
                    }
                    switch (cellIndex){
                        case 1: {
                            student.setMasv(cell.getStringCellValue());
                            break;
                        }
                        case 2: {
                            student.setTensinhvien(cell.getStringCellValue());
                            break;
                        }
                        case 3: {
                            student.setHs1(cell.getNumericCellValue());
                            break;
                        }
                        case 4: {
                            student.setHs2(cell.getNumericCellValue());
                            break;
                        }
                        case 5: {
                            if(!"".equals(cell.getStringCellValue())){
                                student.setHs3(Double.parseDouble(cell.getStringCellValue()));
                            }
                            break;
                        }
                        case 6: {
                            if(!"".equals(cell.getStringCellValue())){
                                student.setHs4(Double.parseDouble(cell.getStringCellValue()));
                            }
                            break;
                        }
                        case 7: {
                            if(!"".equals(cell.getStringCellValue())){
                                student.setHs5(Double.parseDouble(cell.getStringCellValue()));
                            }
                            break;
                        }
                        case 8: {
                            student.setSotietnghi((long) cell.getNumericCellValue());
                            break;
                        }
                        default:{

                        }
                    }
                    cellIndex++;
                }
                students.add(student);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return students;
    }
    public static List<ReponseStudentByClassSection> importFinalPoint(InputStream inputStream) {
        List<ReponseStudentByClassSection> students = new ArrayList<>();
        String subjectCode = "";
        try{
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheet("Điểm thi");
            int rowIndex = 1;

            subjectCode = sheet.getRow(0).getCell(1).toString();
            for(Row row:sheet){
                if( rowIndex == 1 || rowIndex == 2){
                    rowIndex ++;
                    continue;
                }

                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                ReponseStudentByClassSection student = new ReponseStudentByClassSection();
                student.setSubjectCode(subjectCode);
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 0: {
                            student.setMasv(cell.getStringCellValue());
                            break;
                        }
                        case 1: {
                            student.setTensinhvien(cell.getStringCellValue());
                            break;
                        }
                        case 2: {
                            student.setFinaltest(cell.getNumericCellValue());
                            break;
                        }
                        default:{

                        }
                    }
                    cellIndex ++;
                }
                students.add(student);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return students;
    }
}
