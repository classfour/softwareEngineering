package com.example.demo.controller.Score_management_system;


import com.example.demo.domain.GpaEntity;
import com.example.demo.domain.ScoreEntity;
import com.example.demo.service.Choose_courseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TScore_query {

    @Autowired
    private Choose_courseService choose_courseService;

    //教师端查询成绩
    @RequestMapping("/tscore_query")
    public String tscore_query(ModelMap model){
        model.addAttribute("model",choose_courseService.studentScore_Query("CS001"));
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
