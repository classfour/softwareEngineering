package com.example.demo.controller.Score_management_system;

import com.example.demo.service.Choose_courseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Score_query_Based_On_course_name {
    @Autowired
    private Choose_courseService choose_courseService;

    @RequestMapping(value = "/score_query/search_course_handle",method = RequestMethod.POST)
    public String Search_course(@RequestParam String course_name, ModelMap model){
        String s=new String();
        s="%"+course_name+"%";
        model.addAttribute("model",choose_courseService.Score_query_course("2016001",s));
        return "index(groupFour)/score_query_test";
    }
}
