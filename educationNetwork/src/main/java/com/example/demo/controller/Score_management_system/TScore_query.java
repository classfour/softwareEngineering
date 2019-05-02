package com.example.demo.controller.Score_management_system;


import com.example.demo.domain.Course_imformation;
import com.example.demo.domain.Course_name;
import com.example.demo.domain.Study_year;
import com.example.demo.service.Choose_courseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TScore_query {

    @Autowired
    private Choose_courseService choose_courseService;

    //教师端查询成
    @RequestMapping("/teacher_course_query")
    @ResponseBody
    public List<Course_imformation> Course_query(){
        return choose_courseService.Course_query("1");
    }

    @RequestMapping("/tscore_query")
    public String tscore_query(@RequestParam(value = "course_name",defaultValue = "empty")String course_name,ModelMap model){
        List<Course_imformation> Course_list=choose_courseService.Course_query("1");
        String Courseid=new String();
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

        model.addAttribute("course_name",Course_list);
        model.addAttribute("select_course_name",new Course_name(course_name));
        model.addAttribute("model",choose_courseService.studentScore_Query(Courseid));
        return "index(groupFour)/tscore_query";
    }

    @RequestMapping(value = "/tscore_query/search_course",method = RequestMethod.POST)
    public String Search_course(@RequestParam String course_number, ModelMap model){
        String s=new String();
        s="%"+course_number+"%";
        model.addAttribute("model",choose_courseService.studentScore_Query(course_number));
        return "/index(groupFour)/tscore_query";
    }

}
