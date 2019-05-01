package com.example.demo.controller.Score_management_system;

import com.example.demo.domain.EachSubjectRank;
import com.example.demo.domain.GetStudentCourseNumber;
import com.example.demo.domain.ScoreRank;
import org.springframework.stereotype.Controller;
import com.example.demo.domain.ScoreEntity;
import com.example.demo.service.Choose_courseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;
//查询单科成绩
@Controller
public class Score_single_rank {
    @Autowired
    private Choose_courseService choose_courseService;
    @RequestMapping(value = "/score_rank/singlerank",method = RequestMethod.POST)
    public String Search_single_rank(@RequestParam("single_score") String course_name,ModelMap model){
        String s=new String();
        s="%"+course_name+"%";
        EachSubjectRank get_single_rank=test(s);
        model.addAttribute("model",get_single_rank);
        return "/index(groupFour)/score_rank_test";
    }
    @ResponseBody
    @GetMapping("/test/singlerank")
    //test中应该传入学号做参数
    public EachSubjectRank test(String course_name){
        List<ScoreRank> sort_list=choose_courseService.Score_single_rank(course_name);
        Collections.sort(sort_list, new Comparator<ScoreRank>() {
            @Override
            public int compare(ScoreRank h1, ScoreRank h2) {
                double temp=h2.getStu_total() - h1.getStu_total();
                return (int)temp;
            }
        });
        for (int i=0;i<sort_list.size();i++)
        {
            if(sort_list.get(i).getStu_num().equals("2016001"))//此处应该传入登录者的学号
            {
                EachSubjectRank e=new EachSubjectRank(sort_list.get(i).getCoursename(),i+1);
                return e;
            }
        }
        return null;//查不到就返回空
    }
}
