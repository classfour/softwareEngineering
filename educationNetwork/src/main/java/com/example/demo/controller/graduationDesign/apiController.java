package com.example.demo.controller.graduationDesign;

import com.example.demo.domain.Label;
import com.example.demo.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class apiController {
    @Autowired
    LabelService labelService;

    @GetMapping("getSubject")
    public String getSubject(String name) {
//        String[] labelArray = label.split(",");
//        String[] nameArray = name.split(",");

        String label = labelService.getSubjectNumber(name);
        return label;
    }
}
