package com.example.demo.controller.Score_management_system;

import com.example.demo.domain.Course_imformation;
import com.example.demo.domain.Score_excel;
import com.example.demo.domain.StudentScore;
import com.example.demo.service.Choose_courseService;
import com.example.demo.service.CookiesService;
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
import java.util.*;

@Controller
public class Score_upload {
    @Autowired
    private Choose_courseService choose_courseService;
    @Autowired
    private CookiesService cookiesService;//新加获取cookie
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

        Check check=new Check("1");

        for(int i=1;i<=sheet.getLastRowNum();i++){
            Row row=sheet.getRow(i);
            if(row!=null){
                for(int j=0;j<8;j++){
                    tmp=row.getCell(j);
                    tmp.setCellType(Cell.CELL_TYPE_STRING);
                    cell[j]=tmp.getStringCellValue();
                }
                if(check.isok(cell[0],cell[1])){
                    Score_excel excel=new Score_excel(cell);
                    choose_courseService.SetScore(excel);
                }
                else{
                    return "index(groupFour)/error";
                }
            }
        }
        return "redirect:/tscore_query/import_score";
    }

    //对教师导入的成绩信息进行检验(课程号必须与教师所教课程相对应，学生学号必须与教师所教学生学号相对应)
    class Check{

        List<Course_imformation> course_imformationList=null;
        Map<String,List<String>> mp=null;

        Check(String teacher_id){
            course_imformationList=choose_courseService.Course_query(teacher_id);
            mp=new HashMap<String,List<String>>();

            for(Course_imformation e:course_imformationList){
                mp.put(e.getCourse_number(),choose_courseService.student_number_query(e.getCourse_number()));
                Collections.sort(mp.get(e.getCourse_number()),new CMP());//对学生学号进行升序排序，方便以后二分查找
            }
        }

        boolean isok(String course_number,String student_number){
            List<String> ret=mp.get(course_number);
            if(ret==null) {
                return false;
            }

            int pos=Collections.binarySearch(ret,student_number,new CMP());
            if(pos>=0){
                return true;
            }
            else{
                return false;
            }
        }
    }

    class CMP implements Comparator<String>{
        @Override
        public int compare(String o1,String o2){
            return  o1.compareTo(o2);
        }
    }

}
