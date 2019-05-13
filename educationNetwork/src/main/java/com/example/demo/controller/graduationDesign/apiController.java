//package com.example.demo.controller.graduationDesign;
//
//import com.example.demo.domain.ChooseSubject;
//import com.example.demo.domain.GraduationSubject;
//import com.example.demo.domain.Label;
//import com.example.demo.service.ChooseSubjectService;
//import com.example.demo.service.GraduationSubjectService;
//import com.example.demo.service.LabelService;
//import com.example.demo.service.SubjectResultsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.stream.StreamSupport;
//
//@RestController
//@RequestMapping("api")
//public class apiController {
//    @Autowired
//    LabelService labelService;
//    @Autowired
//    ChooseSubjectService chooseSubjectService;
//    @Autowired
//    GraduationSubjectService graduationSubjectService;
//    @Autowired
//    SubjectResultsService subjectResultsService;
//
//    /**
//    * @Description: 根据查询标签返回对应的课题
//    * @Param: [name],多个标签名用逗号分开
//    * @return: java.lang.String ,多个课题编号用,分开
//    * @Author: klx
//    * @Date: 2019-04-28
//    */
////    @GetMapping("getSubject")
////    public String[] getSubject(String label) {
//////        return labelService.getSubjectNumber(label, []);
////    }
//
//    @PostMapping("insertChoose")
//    public boolean insertChoose(String studentNumber, String courseNumber) {
//        ChooseSubject chooseSubject = new ChooseSubject();
//        System.out.println(studentNumber + courseNumber);
//        chooseSubject.setCourseNumber(courseNumber);
//        chooseSubject.setStudentNumber(studentNumber);
//        return chooseSubjectService.insertChoose(chooseSubject);
//    }
//
//    @GetMapping("selectChoose")
//    public int selectChoose(String courseNumber) {
//        return chooseSubjectService.selectChoose(courseNumber);
//    }
//
//    @PostMapping("insertSubject")
//    public boolean insertSubject(GraduationSubject graduationSubject) {
//        System.out.println(graduationSubject.getTeacherNumber());
//        graduationSubject.setStatus(0);
//        graduationSubject.setMax(3);
//        graduationSubject.setMaxNumber(10);
//        graduationSubject.setNumber(0);
////        return true;
//        return graduationSubjectService.insertSubject(graduationSubject);
//    }
//
//    @PostMapping("updateContent")
//    public boolean updateContent(String name, String introduce, String serialnumber){
//        return graduationSubjectService.updateContent(name, introduce, serialnumber);
//    }
//
//    @PostMapping("updateResult")
//    public boolean updateResults(int result, String studentNumber) {
//        return subjectResultsService.updateResult(result, studentNumber);
//    }
//
//    @PostMapping("submit")
//    public boolean submit(String title, String content, String studentNumber) {
//        return subjectResultsService.submit(title, content, studentNumber);
//    }
//
//    @GetMapping("selectResult")
//    public int selectResult(String studentNumber) {
//        return subjectResultsService.selectResult(studentNumber);
//    }
//
//    @PostMapping("appeal")
//    public boolean appeal(int status, String studentNumber) {
//        return subjectResultsService.updateStatus(status, studentNumber);
//    }
//
//    @GetMapping("getAllLabel")
//    public Label[] getAlllabel() {
//        return labelService.getAllLabel();
//    }
//}
