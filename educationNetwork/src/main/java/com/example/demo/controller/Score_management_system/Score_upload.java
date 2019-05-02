package com.example.demo.controller.Score_management_system;

import com.example.demo.domain.Score_excel;
import com.example.demo.service.Choose_courseService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class Score_upload {
    @Autowired
    private Choose_courseService choose_courseService;

    @RequestMapping("/tscore_query/import_score")
    public String score_upload(){
        return "index(groupFour)/tscore_query_ScoreImport";
    }

    @RequestMapping(value = "/upload_score",method = RequestMethod.POST)
    public String uploadScore(@RequestParam MultipartFile file) throws IOException {
        if(file==null)
            return "index(groupFour)/error";
        String fileName=file.getOriginalFilename();
        if(!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")){
            return "index(groupFour)/error";
        }
        boolean isExcel2003=true;
        if(fileName.matches("^.+\\.(?i)(xlsx)$")){
            isExcel2003=false;
        }
        InputStream stream=file.getInputStream();
        Workbook web=null;
        if(isExcel2003){
            web=new HSSFWorkbook(stream);
        }
        else{
            web=new XSSFWorkbook(stream);
        }
        Sheet sheet=web.getSheetAt(0);
        String cell[]=new String[8];
        Cell tmp;
        System.out.println(sheet.getLastRowNum());
        for(int i=1;i<=sheet.getLastRowNum();i++){
            Row row=sheet.getRow(i);
            if(row!=null){
                for(int j=0;j<8;j++){
                    tmp=row.getCell(j);
                    tmp.setCellType(Cell.CELL_TYPE_STRING);
                    cell[j]=tmp.getStringCellValue();
                }
                Score_excel excel=new Score_excel(cell);
                choose_courseService.SetScore(excel);
            }
        }
        return "redirect:/tscore_query/import_score";
    }
}
