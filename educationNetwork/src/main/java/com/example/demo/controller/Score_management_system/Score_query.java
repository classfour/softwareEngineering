package com.example.demo.controller.Score_management_system;

import com.example.demo.domain.*;
import com.example.demo.service.Choose_courseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class Score_query {
    @Autowired
    private Choose_courseService choose_courseService;

    private int status=0;//0随机显示，1升序显示，2降序显示

    @RequestMapping("/score_sort_up")
    public String score_sort_up(){
        status=1;
        return "redirect:/score_query";
    }

    @RequestMapping("/score_sort_down")
    public String score_sort_down(){
        status=2;
        return "redirect:/score_query";
    }

    @RequestMapping("/score_query")
    public String score_query(@RequestParam(value = "study_year",defaultValue = "all") String study_year,ModelMap model){
        boolean f=false;
        if(study_year.equals("all")){
            f=true;
        }
        List<ScoreEntity> lst=choose_courseService.Score_query("2016001");
        if(f){
            if(status==1){
                Collections.sort(lst,new Score_sort_up());
            }
            else if(status==2){
                Collections.sort(lst,new Score_sort_down());
            }
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
            if(status==1){
                Collections.sort(new_lst,new Score_sort_up());
            }
            else if(status==2){
                Collections.sort(new_lst,new Score_sort_down());
            }
            model.addAttribute("select",new Study_year(study_year));
            model.addAttribute("model",new_lst);
        }
        return "/index(groupFour)/score_query";
    }

    @RequestMapping(value = "/score_query_json/{study_year}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> score_query_echart(@PathVariable("study_year") String study_year){
        boolean f=false;
        if(study_year.equals("all")){
            f=true;
        }
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

    //统计成绩分别在[90,100],[80,89].[70,79],[60,69],[0,59]区间的课程数目
    @RequestMapping(value = "/score_query_pie_json/{study_year}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> score_query_echart_pie(@PathVariable("study_year") String study_year){
        boolean f=false;
        if(study_year.equals("all")){
            f=true;
        }
        Map<String,Object> mp=new HashMap<String,Object>();
        List<ScoreEntity> lst=choose_courseService.Score_query("2016001");
        List<Integer> arr=new ArrayList<Integer>();
        int[] cnt=new int[5];
        for(ScoreEntity e:lst){
            if(f){
                int score=(int)(e.getTotal()+0.5);
                cnt[cal(score)]++;
            }
            else{
                if(e.getStudy_year().equals(study_year)){
                    int score=(int)(e.getTotal()+0.5);
                    cnt[cal(score)]++;
                }
            }
        }
        for(int i=0;i<5;i++){
            arr.add(cnt[i]);
        }
        mp.put("cnt",arr);
        return mp;
    }

    public static int cal(int x){
        if(x>=90){
            return 0;
        }
        else if(x>=80&&x<=89){
            return 1;
        }
        else if(x>=70&&x<=79){
            return 2;
        }
        else if(x>=60&&x<=69){
            return 3;
        }
        return 4;
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
