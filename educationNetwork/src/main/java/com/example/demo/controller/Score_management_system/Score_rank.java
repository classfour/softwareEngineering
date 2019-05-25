package com.example.demo.controller.Score_management_system;


import com.example.demo.domain.*;
import com.example.demo.service.Choose_courseService;
import com.example.demo.service.CookiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.List;
//成绩排名显示业务逻辑处理
@Controller
public class Score_rank {
    @Autowired
    private Choose_courseService choose_courseService;
    @Autowired
    private CookiesService cookiesService;//新加获取cookie
    @RequestMapping("/score_rank")//这里也应该获取登录者的学号做形参输入
    public String score_rank(@RequestParam(value = "study_year",defaultValue = "all") String study_year,ModelMap model){
        if(!cookiesService.isLogin()){
            model.addAttribute("msg", "请先登陆");
            model.addAttribute("url", "/login");

            return "graduationDesign/error";
        }

        boolean f=false;
        if(study_year.equals("all")){
            f=true;
        }
        List<GetStudentCourseNumber> allcourse=testcourse();
        List<EachSubjectRank> show_rank=new ArrayList<EachSubjectRank>();
        for(int i=0;i<allcourse.size();i++)
        {
            if(f||allcourse.get(i).getStudy_year().equals(study_year)){
                EachSubjectRank temp= Get_subject_rank(allcourse.get(i).getCoursenumber());
                show_rank.add(temp);
            }
        }
//        return show_rank;
        model.addAttribute("select",new Study_year(study_year));
        model.addAttribute("model",show_rank);
        return "index(groupFour)/score_rank";
    }
    @ResponseBody
    @GetMapping("/testrank")
    //Get_subject_rank中应该传入学号做参数
    public EachSubjectRank Get_subject_rank(String coursenumber){
        List<ScoreAll> sort_list=choose_courseService.Score_All(coursenumber);
        String user_name=cookiesService.getCookies("username");//新加cookie
        Collections.sort(sort_list, new Comparator<ScoreAll>() {
            @Override
            public int compare(ScoreAll h1, ScoreAll h2) {
                double temp=h2.getStu_total() - h1.getStu_total();
                return (int)temp;
            }
        });
        for (int i=0;i<sort_list.size();i++)
        {
//            if(sort_list.get(i).getStu_num().equals("2016001"))//此处应该传入登录者的学号
            if(sort_list.get(i).getStu_num().equals(user_name))//此处应该传入登录者的学号
            {
                EachSubjectRank e=new EachSubjectRank(sort_list.get(i).getCoursename(),i+1,sort_list.get(i).getStudy_year());
                return e;
            }
        }
        return null;//查不到就返回空
    }
    @ResponseBody
    @GetMapping("/testgetcourse")
    //test中应该传入学号做参数
    public List<GetStudentCourseNumber> testcourse(){
        String user_name=cookiesService.getCookies("username");//新加cookie
//        return choose_courseService.Student_Course_Number("2016001");
        return choose_courseService.Student_Course_Number(user_name);
    }
}
