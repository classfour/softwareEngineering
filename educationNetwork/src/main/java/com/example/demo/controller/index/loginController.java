package com.example.demo.controller.index;

import com.example.demo.domain.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.List;

@Controller
public class loginController {
    @Autowired
    CookiesService cookiesService;
    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    NoticeService noticeService;
    @Autowired
    Choose_courseService choose_courseService;

    @RequestMapping("login")
    public String login(String usernmae, String password) {
        return "index/login";
    }
    @RequestMapping("home")
    public String index(Model model, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-store");


        if(!cookiesService.isLogin()){
            model.addAttribute("msg", "请先登陆");
            model.addAttribute("url", "/login");

            return "graduationDesign/error";
        }
        String lv = cookiesService.getCookies("lv");
        Notice[] notices = noticeService.getAllNotice();
        if(lv.equals("0")) {
            Student student = studentService.selectStudent(cookiesService.getCookies("username"));
            model.addAttribute("name", student.getName());
            model.addAttribute("lv", "学生");
            model.addAttribute("college", student.getDepartments());
            List<ScoreEntity> lst=choose_courseService.Score_query(student.getNumber());
            if(lst.size()>5){//只显示前5条成绩
                List<ScoreEntity> new_lst=new ArrayList<ScoreEntity>();
                for(int i=0;i<5;i++){
                    new_lst.add(lst.get(i));
                }
                model.addAttribute("score",new_lst);
            }
            else{
                model.addAttribute("score",lst);
            }
        }else if(lv.equals("1")){
            Teacher teacher = teacherService.selectByNumber(cookiesService.getCookies("username"));
            model.addAttribute("name", teacher.getName());
            model.addAttribute("lv", "教师");
            model.addAttribute("college", teacher.getDepartments());
        }else{
            model.addAttribute("name", "管理员");
            model.addAttribute("lv", "管理员");
            model.addAttribute("college", "-");
        }
        model.addAttribute("notice", notices);
        cookiesService.isLogin();
//        System.out.println(notices[0].getContent());
        return "index/index";
    }
    @RequestMapping("submit")
    public String submit(String username, String password, Model model) {
        User user = userService.select(username, password);
        System.out.println("user_name=="+user.getUsername());
//        System.out.println(user.getUsername());
        if(user == null) {
            //重定向
            model.addAttribute("msg", "用户名或密码错误");
            model.addAttribute("url", "/login");
            return "graduationDesign/error";
        }else{
            cookiesService.setCookies("username", username);
            cookiesService.setCookies("lv", user.getLevel());
            return "redirect:/home";
        }
    }
}
