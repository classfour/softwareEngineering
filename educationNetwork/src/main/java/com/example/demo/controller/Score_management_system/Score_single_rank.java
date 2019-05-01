package com.example.demo.controller.Score_management_system;

import com.example.demo.domain.EachSubjectRank;
import com.example.demo.domain.ScoreAll;
import com.example.demo.domain.Study_year;
import org.springframework.stereotype.Controller;
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
    public String Search_single_rank(@RequestParam(value = "single_score",defaultValue = "") String course_name,@RequestParam(value = "study_year",defaultValue = "all")String study_year, ModelMap model){
        String s=new String();
        s="%"+course_name+"%";
        List<EachSubjectRank> get_single_rank=Get_subject_rank(s,study_year);
        model.addAttribute("model",get_single_rank);
        model.addAttribute("select",new Study_year(study_year));
        return "/index(groupFour)/score_rank_test";
    }
    @ResponseBody
    @GetMapping("/test/singlerank")
    //Get_subject_rank中应该传入学号做参数
    public List<EachSubjectRank> Get_subject_rank(String course_name,String study_year){
        List<ScoreAll> sort_list=choose_courseService.Score_single_rank(course_name);
        List<ScoreAll> new_sort_lst=new ArrayList<ScoreAll>();

        if(!study_year.equals("all")){
            for(ScoreAll e:sort_list){
                if(e.getStudy_year().equals(study_year)){
                    new_sort_lst.add(e);
                }
            }
        }
        else{
            new_sort_lst=sort_list;
        }

        //将选出的成绩按照课程号分类
        Map<String,List<ScoreAll>> mp=new HashMap<String,List<ScoreAll>>();
        for(ScoreAll e:new_sort_lst){
            mp.put(e.getCoursenum(),new ArrayList<ScoreAll>());
        }

        for(ScoreAll e:new_sort_lst){
            mp.get(e.getCoursenum()).add(e);
        }

        for(ScoreAll e:new_sort_lst){
            Collections.sort(mp.get(e.getCoursenum()),new CMP());
        }

        List<EachSubjectRank> ans=new ArrayList<EachSubjectRank>();
        ScoreAll data;

        for(ScoreAll e:new_sort_lst){
            List<ScoreAll> lst=mp.get(e.getCoursenum());
            for(int i=0;i<lst.size();i++){
                data=lst.get(i);
                if(data.getStu_num().equals("2016001")){
                    ans.add(new EachSubjectRank(data.getCoursename(),i+1,data.getStudy_year()));
                    break;
                }
            }
        }

        return ans;//查不到就返回空
    }

    class CMP implements Comparator<ScoreAll>{
        @Override
        public int compare(ScoreAll o1,ScoreAll o2){
            if(o1.getStu_total()>o2.getStu_total())
                return -1;
            return 1;
        }
    }
}
