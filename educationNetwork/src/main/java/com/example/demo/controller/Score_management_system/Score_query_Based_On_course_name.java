package com.example.demo.controller.Score_management_system;

import com.example.demo.domain.ScoreEntity;
import com.example.demo.domain.Study_year;
import com.example.demo.service.Choose_courseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Score_query_Based_On_course_name {
    @Autowired
    private Choose_courseService choose_courseService;

    @RequestMapping(value = "/score_query/search_course",method = RequestMethod.POST)
    public String Search_course(@RequestParam(value = "course_name",defaultValue = "") String course_name, @RequestParam(value = "study_year",defaultValue = "all") String study_year,ModelMap model){
        boolean f=false;
        if(study_year.equals("all")){
            f=true;
        }
        String s=new String();
        s="%"+course_name+"%";
        List<ScoreEntity> lst=choose_courseService.Score_query_course("2016001",s);
        if(!f){
            List<ScoreEntity> new_lst=new ArrayList<ScoreEntity>();
            for(ScoreEntity e:lst){
                if(e.getStudy_year().equals(study_year)){
                    new_lst.add(e);
                }
            }
            model.addAttribute("model",new_lst);
        }
        else{
            model.addAttribute("model",lst);
        }
        model.addAttribute("select",new Study_year(study_year));
        System.out.println("course_name=="+course_name);
        System.out.println("study_year=="+study_year);

        return "/index(groupFour)/score_query_based_on_name";
    }
}
    