package com.example.demo.controller.Score_management_system;

import com.example.demo.domain.ScoreEntity;
import com.example.demo.service.Choose_courseService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
public class Score_export {
    @Autowired
    private Choose_courseService choose_courseService;

    @RequestMapping(value = "/score_query/score_export",method = RequestMethod.POST)
    public void score_export(HttpServletResponse response)throws IOException {
        List<ScoreEntity> lst=choose_courseService.Score_query("2016001");
        File file=new File("score.xlsx");
        Workbook workbook=new XSSFWorkbook();
        CellStyle cellStyle=getColumnTopStyle(workbook);
        Sheet sheet=workbook.createSheet("课程成绩");
        int cnt=0;
        Row row0=sheet.createRow(cnt++);

        sheet.setColumnWidth(0,20*256);
        row0.createCell(0).setCellValue("课程名称");
        sheet.setColumnWidth(1,20*256);
        row0.createCell(1).setCellValue("修读时间");
        row0.createCell(2).setCellValue("课程成绩");
        row0.createCell(3).setCellValue("课程绩点");
        for(ScoreEntity e:lst){
            Row row=sheet.createRow(cnt++);
            row.createCell(0).setCellValue(e.getCoursename());
            row.createCell(1).setCellValue(e.getStudy_year());
            row.createCell(2).setCellValue(e.getTotal());
            row.createCell(3).setCellValue(e.getGpa());
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

    // 设置单元格边框样式
    // CellStyle.BORDER_DOUBLE      双边线
    // CellStyle.BORDER_THIN        细边线
    // CellStyle.BORDER_MEDIUM      中等边线
    // CellStyle.BORDER_DASHED      虚线边线
    // CellStyle.BORDER_HAIR        小圆点虚线边线
    // CellStyle.BORDER_THICK       粗边线
    public CellStyle getColumnTopStyle(Workbook workbook){
        CellStyle cellStyle=workbook.createCellStyle();
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        //设置自动换行
        cellStyle.setWrapText(false);
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        return cellStyle;
    }
}
