package com.example.demo.controller.Score_management_system;

import com.example.demo.domain.Course_imformation;
import com.example.demo.domain.ScoreEntity;
import com.example.demo.domain.StudentScore;
import com.example.demo.service.Choose_courseService;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static com.example.demo.controller.Score_management_system.Score_export.getColumnTopStyle;

@Controller
public class Score_export_teacher {
    @Autowired
    private Choose_courseService choose_courseService;

    @RequestMapping(value = "/tscore_query/score_export",method = RequestMethod.POST)
    public void score_export_teacher(@RequestParam("course_name") String course_name, HttpServletResponse response) throws IOException {
        List<Course_imformation> lst=choose_courseService.Course_query("1");
        String course_id=null;
        for(Course_imformation e:lst){
            if(e.getCourse_name().equals(course_name)){
                course_id=e.getCourse_number();
                break;
            }
        }
        List<StudentScore> stu=choose_courseService.studentScore_Query(course_id);

        File file=new File("student_score"+"("+course_name+")"+".xlsx");
        Workbook workbook=new XSSFWorkbook();
        CellStyle cellStyle=getColumnTopStyle(workbook);
        Sheet sheet=workbook.createSheet("学生课程成绩");
        int cnt=0;
        Row row0=sheet.createRow(cnt++);

        sheet.setColumnWidth(0,20*256);
        row0.createCell(0).setCellValue("课程名称");
        sheet.setColumnWidth(1,20*256);
        row0.createCell(1).setCellValue("学生学号");
        sheet.setColumnWidth(2,20*256);
        row0.createCell(2).setCellValue("学生姓名");
        sheet.setColumnWidth(3,20*256);
        row0.createCell(3).setCellValue("学生班级");

        row0.createCell(4).setCellValue("平时成绩");
        sheet.setColumnWidth(5,20*256);
        row0.createCell(5).setCellValue("平时成绩占比");
        row0.createCell(6).setCellValue("期中成绩");
        sheet.setColumnWidth(7,20*256);
        row0.createCell(7).setCellValue("期中成绩占比");
        row0.createCell(8).setCellValue("期末成绩");
        sheet.setColumnWidth(9,20*256);
        row0.createCell(9).setCellValue("期末成绩占比");
        row0.createCell(10).setCellValue("课程绩点");
        row0.createCell(11).setCellValue("总评成绩");

        for(StudentScore e:stu){
            Row row=sheet.createRow(cnt++);
            row.createCell(0).setCellValue(e.getScore().getCoursename());
            row.createCell(1).setCellValue(e.getStudentnumber());
            row.createCell(2).setCellValue(e.getStudentname());
            row.createCell(3).setCellValue(e.getStudentclass());
            row.createCell(4).setCellValue(e.getScore().getScoreUsual());
            row.createCell(5).setCellValue(e.getScore().getScoreUsualRate());
            row.createCell(6).setCellValue(e.getScore().getScoreMid());
            row.createCell(7).setCellValue(e.getScore().getScoreMidRate());
            row.createCell(8).setCellValue(e.getScore().getScoreFinal());
            row.createCell(9).setCellValue(e.getScore().getScoreFinalRate());
            row.createCell(10).setCellValue(e.getScore().getGpa());
            row.createCell(11).setCellValue(e.getScore().getTotal());
        }
        for(int i=0;i<=sheet.getLastRowNum();i++){
            Row rowStyle=sheet.getRow(i);
            for(int j=0;j<rowStyle.getLastCellNum();j++){
                rowStyle.getCell(j).setCellStyle(cellStyle);
            }
        }

        response.reset();
        response.setHeader("Content-Disposition","attachment;filename=\""+new String(file.getName().getBytes("utf-8"),"ISO8859-1")+"\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream out=new BufferedOutputStream(response.getOutputStream());
        workbook.write(out);
        out.flush();
        if(out!=null){
            out.close();
        }
    }

}
