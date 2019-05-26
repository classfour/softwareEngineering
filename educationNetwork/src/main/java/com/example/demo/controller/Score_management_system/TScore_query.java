package com.example.demo.controller.Score_management_system;


import com.example.demo.domain.Course_imformation;
import com.example.demo.domain.Course_name;
import com.example.demo.domain.StudentScore;
import com.example.demo.domain.Study_year;
import com.example.demo.service.Choose_courseService;
import com.example.demo.service.CookiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class TScore_query {

    @Autowired
    private Choose_courseService choose_courseService;
    private int status=0;//0乱序,1学号升序,2学号降序,3总成绩升序,4总成绩降序
    private String lst_coursename=null;
    @Autowired
    private CookiesService cookiesService;//新加获取cookie
    @RequestMapping("/tscore_query/studentnumber_sort_up/{course_name}")
    public String studentnumber_sort_up(@PathVariable("course_name")String course_name){
        lst_coursename=course_name;
        status=1;
        return "redirect:/tscore_query";
    }

    @RequestMapping("/tscore_query/studentnumber_sort_down/{course_name}")
    public String studentnumber_sort_down(@PathVariable("course_name")String course_name){
        lst_coursename=course_name;
        status=2;
        return "redirect:/tscore_query";
    }

    @RequestMapping("/tscore_query/studentscore_sort_up/{course_name}")
    public String studentscore_sort_up(@PathVariable("course_name")String course_name){
        lst_coursename=course_name;
        status=3;
        return "redirect:/tscore_query";
    }

    @RequestMapping("/tscore_query/studentscore_sort_down/{course_name}")
    public String studentscore_sort_down(@PathVariable("course_name")String course_name){
        lst_coursename=course_name;
        status=4;
        return "redirect:/tscore_query";
    }

    //查询学生成绩(教师端)
    @RequestMapping("/teacher_course_query")
    @ResponseBody
    public List<Course_imformation> Course_query(){
        String user_name=cookiesService.getCookies("username");//新加cookie
//        return choose_courseService.Course_query("1");
        return choose_courseService.Course_query(user_name);
    }

    @RequestMapping("/tscore_query")
    public String tscore_query(@RequestParam(value = "course_name",defaultValue = "empty")String course_name,ModelMap model){
        if(!cookiesService.isLogin()){
            model.addAttribute("msg", "请先登陆");
            model.addAttribute("url", "/login");

            return "graduationDesign/error";
        }

        String user_name=cookiesService.getCookies("username");//新加cookie
//        List<Course_imformation> Course_list=choose_courseService.Course_query("1");
        List<Course_imformation> Course_list=choose_courseService.Course_query(user_name);
        String Courseid=new String();

        if(lst_coursename!=null){
            course_name=lst_coursename;
            lst_coursename=null;
        }

        if(course_name.equals("empty")){
            if(!Course_list.isEmpty()){
                Courseid=Course_list.get(0).getCourse_number();
                course_name=Course_list.get(0).getCourse_name();
            }
        }
        else{
            for(Course_imformation e:Course_list){
                if(e.getCourse_name().equals(course_name)){
                    Courseid=e.getCourse_number();
                    break;
                }
            }
        }
        List<StudentScore> studentScores=choose_courseService.studentScore_Query(Courseid);
        if(status==1){
            Collections.sort(studentScores,new Student_number_up());
        }
        else if(status==2){
            Collections.sort(studentScores,new Student_number_down());
        }
        else if(status==3){
            Collections.sort(studentScores,new Student_score_up());
        }
        else if(status==4){
            Collections.sort(studentScores,new Student_score_down());
        }

        model.addAttribute("course_name",Course_list);
        model.addAttribute("select_course_name",new Course_name(course_name));
        model.addAttribute("model",studentScores);
        return "index(groupFour)/tscore_query";
    }

    class Student_number_up implements Comparator<StudentScore> {
        @Override
        public int compare(StudentScore o1,StudentScore o2){
            return o1.getStudentnumber().compareTo(o2.getStudentnumber());
        }
    }

    class Student_number_down implements Comparator<StudentScore> {
        @Override
        public int compare(StudentScore o1,StudentScore o2){
            return -(o1.getStudentnumber().compareTo(o2.getStudentnumber()));
        }
    }

    class Student_score_up implements Comparator<StudentScore> {
        @Override
        public int compare(StudentScore o1,StudentScore o2){
            return (int)(o1.getScore().getTotal()+0.5)-(int)(o2.getScore().getTotal()+0.5);
        }
    }

    class Student_score_down implements Comparator<StudentScore> {
        @Override
        public int compare(StudentScore o1,StudentScore o2){
            return (int)(o2.getScore().getTotal()+0.5)-(int)(o1.getScore().getTotal()+0.5);
        }
    }
}
