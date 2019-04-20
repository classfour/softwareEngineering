package com.example.demo.controller.Score_query;


import com.example.demo.domain.ScoreEntity;
import com.example.demo.service.Choose_courseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class Score_query {
    @Autowired
    private Choose_courseService choose_courseService;

    @RequestMapping("/score_query")
    public String sore_query(ModelMap model){
        model.addAttribute("model",choose_courseService.Score_query("2016001"));
        return "index(groupFour)/score_query_test";
    }
    @ResponseBody
    @GetMapping("/test")
    public List<ScoreEntity> test(){
        return choose_courseService.Score_query("2016001");
    }


}
