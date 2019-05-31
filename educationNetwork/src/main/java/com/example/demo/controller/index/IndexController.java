package com.example.demo.controller.index;

import com.example.demo.domain.Student;
import com.example.demo.domain.Teacher;
import com.example.demo.service.CookiesService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;

@Controller
public class IndexController {
    @Autowired
    StudentService studentService;
    @Autowired
    CookiesService cookiesService;
    @Autowired
    TeacherService teacherService;
    @RequestMapping("/index")
    public String index(Model model) {


        return "index/index";
    }

    @RequestMapping("/loginout")
    public String loginout(Model model) {
        cookiesService.setCookies("username", "");

        return "redirect:/login";
    }
}
