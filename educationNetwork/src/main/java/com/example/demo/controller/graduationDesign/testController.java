package com.example.demo.controller.graduationDesign;

import com.example.demo.domain.GraduationSubject;
import com.example.demo.service.GraduationSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("graduationDesign")
public class testController {
    @Autowired
    private GraduationSubjectService graduationSubjectService;

    @GetMapping("select")
    public String test(ModelMap modelMap) {
        GraduationSubject graduationSubject = graduationSubjectService.getSubject("001");
        modelMap.addAttribute("graduationSubject", graduationSubject);
        return "graduationDesign/test";
    }
}
