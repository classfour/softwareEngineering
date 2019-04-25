package com.example.demo.controller.Score_management_system;

import com.example.demo.domain.StudentScore;
import com.example.demo.service.Choose_courseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class studentScore_query {
    @Autowired
    private Choose_courseService choose_courseService;

    @ResponseBody
    @RequestMapping("/student_score_query")
    public List<StudentScore> student_score_query(String coursenumber){
        return choose_courseService.studentScore_Query("CS001");
    }
}
