package com.example.demo.controller.Score_management_system;


import com.example.demo.domain.GpaEntity;
import com.example.demo.domain.ScoreEntity;
import com.example.demo.service.Choose_courseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.*;

@Controller
public class GPA_query {
    @Autowired
    private Choose_courseService choose_courseService;

    @RequestMapping("/GPA_query")
    public String gpa_query(){
        return "/index(groupFour)/GPA_query";
    }

    @RequestMapping(value = "/GPA_query_json",method = RequestMethod.GET)
    @ResponseBody
//    按照课程修读年份整理学生的GPA
    public Map<String,Object> gpa_query_json(){
        List<GpaEntity> lst=choose_courseService.Gpa_query("2016001");
        Map<String,ArrayList<GpaEntity>> mp=new HashMap<String,ArrayList<GpaEntity>>();
        List<String> study_year_list=new ArrayList<String>();
        List<String> GPA=new ArrayList<String>();//为了显示末尾0，用String类型
        Map<String,Object> ans=new LinkedHashMap<String, Object>();

        for(GpaEntity e:lst){
            if(mp.get(e.getStudy_year())==null){
                mp.put(e.getStudy_year(),new ArrayList<GpaEntity>());
                study_year_list.add(e.getStudy_year());
            }
        }

        Collections.sort(study_year_list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.compareTo(o2)<0)
                    return -1;
                else
                    return 1;
            }
        });

        for(GpaEntity e:lst){
            mp.get(e.getStudy_year()).add(e);
        }

        double sum,credit;
        double SUM=0,CREDIT=0;//全年总学分*绩点之和，学分之和
        DecimalFormat df = new DecimalFormat("#.00");
        for(String s:study_year_list){
            ArrayList<GpaEntity> element=mp.get(s);
            sum=0;
            credit=0;
            for(GpaEntity e:element){
                credit+=e.getCredit();
                sum+=e.getCredit()*e.getGpa();
            }
            SUM+=sum;
            CREDIT+=credit;
            sum=sum/credit;
            GPA.add(df.format(sum));
        }

        ans.put("total",df.format(SUM/CREDIT));
        ans.put("study_year",study_year_list);
        ans.put("GPA",GPA);
        return ans;
    }

}
