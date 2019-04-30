package com.example.demo.controller.graduationDesign;

import com.example.demo.domain.GraduationSubject;
import com.example.demo.domain.Label;
import com.example.demo.service.CookiesService;
import com.example.demo.service.GraduationSubjectService;
import com.example.demo.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("graduationDesign")
public class graduationController {
    @Autowired
    private GraduationSubjectService graduationSubjectService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private CookiesService cookiesService;

    @PostMapping("subjectSubmit")
    public String subjectSubmit(String name, String labelNumber, String type, String introduce) {
//        System.out.println(name);
//        System.out.println(type);
//        System.out.println(introduce);
        System.out.println(labelNumber);
        GraduationSubject graduationSubject = new GraduationSubject();
        graduationSubject.setSerialnumber(labelNumber);
        graduationSubject.setName(name);
        graduationSubject.setIntroduce(introduce);
        graduationSubject.setStatus(0);
        graduationSubject.setMax(3);
        graduationSubject.setMaxNumber(10);
        graduationSubject.setNumber(0);
        String username = cookiesService.getCookies("username");
        graduationSubject.setTeacherNumber(username);
        graduationSubjectService.insertSubject(graduationSubject);
        return "redirect:/graduationDesign/declare";
    }

    @GetMapping("select")
    public String test(ModelMap modelMap) {
        GraduationSubject graduationSubject = graduationSubjectService.getSubject("001");
        modelMap.addAttribute("graduationSubject", graduationSubject);
        return "graduationDesign/test";
    }

    @GetMapping("mystudent")
    public String mystudent() {
        return "graduationDesign/mystudent";
    }
    @GetMapping("declare")
    public String declare(Model model) {
        Label[] label = labelService.getAllLabel();
//        System.out.println(label[0]);
        model.addAttribute("label", label);
        return "graduationDesign/declare";
    }
    @GetMapping("appraise")
    public String appraise() {
        return "graduationDesign/appraise";
    }
    @GetMapping("detail")
    public String detail() {
        return "graduationDesign/Subdetail-teacher";
    }
    @GetMapping("subject")
    public String subject() {
        return "graduationDesign/showSubject";
    }
}
