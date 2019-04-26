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
public class Score_query {
    @Autowired
    private Choose_courseService choose_courseService;

    @RequestMapping("/score_query")
    public String sore_query(ModelMap model){
        model.addAttribute("model",choose_courseService.Score_query("2016001"));
        return "index(groupFour)/score_query_test";
    }
    @RequestMapping(value = "/score_query_json",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> sore_query_echart(){
        List<ScoreEntity> lst=choose_courseService.Score_query("2016001");
        List<Double> score=new ArrayList<Double>();
        List<String> course_name=new ArrayList<String>();
        Map<String,Object> mp=new HashMap<String,Object>();
        for(int i=0;i<lst.size();i++){
            score.add(lst.get(i).getTotal());
            course_name.add(lst.get(i).getCoursename());
        }
        mp.put("course_name",course_name);
        mp.put("score",score);
        return mp;
    }

    @ResponseBody
    @GetMapping("/test")
    public List<ScoreEntity> test(){
        return choose_courseService.Score_query("2016001");
    }

    @ResponseBody
    @GetMapping("/gpatest")
    public List<GpaEntity> gpatest(){
        return choose_courseService.Gpa_query("2016001");
    }



}
