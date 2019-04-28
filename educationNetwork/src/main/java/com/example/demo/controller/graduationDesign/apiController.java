package com.example.demo.controller.graduationDesign;

import com.example.demo.domain.ChooseSubject;
import com.example.demo.domain.Label;
import com.example.demo.service.ChooseSubjectService;
import com.example.demo.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api")
public class apiController {
    @Autowired
    LabelService labelService;
    @Autowired
    ChooseSubjectService chooseSubjectService;

    /**
    * @Description: 根据查询标签返回对应的课题
    * @Param: [name],多个标签名用逗号分开
    * @return: java.lang.String ,多个课题编号用,分开
    * @Author: klx
    * @Date: 2019-04-28
    */
    @GetMapping("getSubject")
    public String[] getSubject(String label) {
        return labelService.getSubjectNumber(label);
    }

    @PostMapping("insertChoose")
    public boolean insertChoose(String studentNumber, String courseNumber) {
        ChooseSubject chooseSubject = new ChooseSubject();
        System.out.println(studentNumber + courseNumber);
        chooseSubject.setCourseNumber(courseNumber);
        chooseSubject.setStudentNumber(studentNumber);
        return chooseSubjectService.insertChoose(chooseSubject);
    }

    @GetMapping("selectChoose")
    public int selectChoose(String courseNumber) {
        return chooseSubjectService.selectChoose(courseNumber);
    }
}
