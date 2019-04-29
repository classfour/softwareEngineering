package com.example.demo.controller.Score_management_system;

import com.example.demo.domain.GpaEntity;
import com.example.demo.domain.ScoreEntity;
import com.example.demo.domain.Study_year;
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
<<<<<<< HEAD
    public String score_query(ModelMap model){
        model.addAttribute("model",choose_courseService.Score_query("2016001"));
=======
    public String score_query(@RequestParam(value = "study_year",defaultValue = "all") String study_year,ModelMap model){
        boolean f=false;
        if(study_year.equals("all")){
            f=true;
        }
        List<ScoreEntity> lst=choose_courseService.Score_query("2016001");
        if(f){
            model.addAttribute("select",new Study_year(study_year));
            model.addAttribute("model",lst);
        }
        else{
            List<ScoreEntity> new_lst=new ArrayList<ScoreEntity>();
            for(ScoreEntity e:lst){
                if(e.getStudy_year().equals(study_year)){
                    new_lst.add(e);
                }
            }
            model.addAttribute("select",new Study_year(study_year));
            model.addAttribute("model",new_lst);
        }
>>>>>>> c3beddc4190bc413692288a73f7face801eb7ae5
        return "index(groupFour)/score_query_test";
    }

    @RequestMapping(value = "/score_query_json/{study_year}",method = RequestMethod.GET)
    @ResponseBody
<<<<<<< HEAD
    public Map<String,Object> score_query_echart(){
=======
    public Map<String,Object> score_query_echart(@PathVariable("study_year") String study_year){
        boolean f=false;
        if(study_year.equals("all")){
            f=true;
        }
>>>>>>> c3beddc4190bc413692288a73f7face801eb7ae5
        List<ScoreEntity> lst=choose_courseService.Score_query("2016001");
        List<Double> score=new ArrayList<Double>();
        List<String> course_name=new ArrayList<String>();
        Map<String,Object> mp=new HashMap<String,Object>();
        for(int i=0;i<lst.size();i++){
            if(f){
                score.add(lst.get(i).getTotal());
                course_name.add(lst.get(i).getCoursename());
            }
            else{
                if(lst.get(i).getStudy_year().equals(study_year)){
                    score.add(lst.get(i).getTotal());
                    course_name.add(lst.get(i).getCoursename());
                }
            }
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
