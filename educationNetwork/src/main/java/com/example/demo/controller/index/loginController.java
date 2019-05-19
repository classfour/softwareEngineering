package com.example.demo.controller.index;

import com.example.demo.domain.Student;
import com.example.demo.domain.Teacher;
import com.example.demo.domain.User;
import com.example.demo.service.CookiesService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import javax.xml.ws.Service;

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

    @RequestMapping("login")
    public String login(String usernmae, String password) {
        return "index/login";
    }
    @RequestMapping("home")
    public String index(Model model) {
        String lv = cookiesService.getCookies("lv");
        if(lv.equals("0")) {
            Student student = studentService.selectStudent(cookiesService.getCookies("username"));
            model.addAttribute("name", student.getName());
            model.addAttribute("lv", "学生");
            model.addAttribute("college", student.getDepartments());
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
        return "index/index";
    }
    @RequestMapping("submit")
    public String submit(String username, String password, Model model) {
        User user = userService.select(username, password);
//        System.out.println(user.getUsername());
        if(user == null) {
            //重定向
            return "redirect:/login";
        }else{
            cookiesService.setCookies("username", username);
            cookiesService.setCookies("lv", user.getLevel());
            return "redirect:/home";
        }
    }
}
