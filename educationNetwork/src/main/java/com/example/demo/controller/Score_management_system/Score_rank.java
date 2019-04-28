package com.example.demo.controller.Score_management_system;


import com.example.demo.domain.*;
import com.example.demo.service.Choose_courseService;
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
    @RequestMapping("/score_rank")//这里也应该获取登录者的学号做形参输入
    public String score_rank(ModelMap model){

        List<GetStudentCourseNumber> allcourse=testcourse();
        List<EachSubjectRank> show_rank=new ArrayList<EachSubjectRank>();
        for(int i=0;i<allcourse.size();i++)
        {

            EachSubjectRank temp=test(allcourse.get(i).getCoursenumber());
            show_rank.add(temp);
        }
//        return show_rank;
        model.addAttribute("model",show_rank);
        return "index(groupFour)/score_rank_test";
    }
    @ResponseBody
    @GetMapping("/testrank")
    //test中应该传入学号做参数
    public EachSubjectRank test(String coursenumber){
        List<ScoreRank> sort_list=choose_courseService.Score_rank(coursenumber);
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
    @ResponseBody
    @GetMapping("/testgetcourse")
    //test中应该传入学号做参数
    public List<GetStudentCourseNumber> testcourse(){
        return choose_courseService.Student_Course_Number("2016001");
    }
}
