package com.example.demo.controller.Score_management_system;

import com.example.demo.domain.Course_imformation;
import com.example.demo.domain.Course_name;
import com.example.demo.domain.StudentScore;
import com.example.demo.service.Choose_courseService;
import com.example.demo.service.CookiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.CoderResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TScore_query_analysis {
    @Autowired
    private Choose_courseService choose_courseService;
    @Autowired
    private CookiesService cookiesService;//新加获取cookie
    @RequestMapping("/tscore_query/analysis")
    public String tscore_query(@RequestParam(value = "course_name",defaultValue = "empty")String course_name, ModelMap model) {
        List<Course_imformation> Course_list = choose_courseService.Course_query("1");
        String Courseid = new String();
        if (course_name.equals("empty")) {
            if (!Course_list.isEmpty()) {
                Courseid = Course_list.get(0).getCourse_number();
                course_name = Course_list.get(0).getCourse_name();
            }
        }
        else {
            for (Course_imformation e : Course_list) {
                if (e.getCourse_name().equals(course_name)) {
                    Courseid = e.getCourse_number();
                    break;
                }
            }
        }
        model.addAttribute("course_name", Course_list);
        model.addAttribute("select_course_name", new Course_name(course_name));
        return "index(groupFour)/tscore_query_analysis";
    }

    @RequestMapping("/tscore_query/analysis_json/{course_name}")
    @ResponseBody
    public Map<String,Object> tscore_query_analysic(@PathVariable("course_name")String course_name){
        List<Course_imformation> Course_list=choose_courseService.Course_query("1");
        String Course_id=new String();
        for(Course_imformation e:Course_list){
            if(e.getCourse_name().equals(course_name)){
                Course_id=e.getCourse_number();
            }
        }
        List<Integer> lst=new ArrayList<Integer>();
        List<StudentScore> stu_score=choose_courseService.studentScore_Query(Course_id);
        int[] cnt=new int[5];
        int score;
        for(StudentScore e:stu_score){
            score=(int)(e.getScore().getTotal()+0.5);
            cnt[Score_query.cal(score)]++;
        }
        for(int i=0;i<5;i++){
            lst.add(cnt[i]);
        }
        Map<String,Object> mp=new HashMap<String,Object>();
        mp.put("cnt",lst);
        return mp;
    }

}
