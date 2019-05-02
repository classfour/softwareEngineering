package com.example.demo.controller.graduationDesign;

import com.example.demo.domain.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.expression.Arrays;
import org.thymeleaf.util.StringUtils;

import javax.jws.WebParam;
import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("graduationDesign")
public class graduationController {
    @Autowired
    private GraduationSubjectService graduationSubjectService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private CookiesService cookiesService;
    @Autowired
    private ChooseSubjectService chooseSubjectService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SubjectResultsService subjectResultsService;

    @GetMapping("checkResult")
    public String checkResult(Model model) {
//        model.addAttribute("year", "");
//        model.addAttribute("term", "");
//        model.addAttribute("result", "");
//        model.addAttribute("subject", "");
//        model.addAttribute("student", "");
        return "graduationDesign/checkResult";
    }

    @PostMapping("findResult")
    public String findResult(String year, String term, Model model) {
        String[] s = year.split("-");
//        System.out.println(s[0]);
//        System.out.println(term);
        if(!s[0].equals("2018") || !term.equals("2")) {
            return "redirect:/graduationDesign/checkResult";
        }
        String username = cookiesService.getCookies("username");
        System.out.println(username);
        SubjectResults subjectResults = subjectResultsService.selectByStudent(username);
        System.out.println(subjectResults.getCourseNumber());
        GraduationSubject graduationSubject = graduationSubjectService.getSubject(subjectResults.getCourseNumber());
        Student student = studentService.selectStudent(username);
        model.addAttribute("year", year);
        model.addAttribute("term", term);
        model.addAttribute("result", subjectResults);
        model.addAttribute("subject", graduationSubject);
        model.addAttribute("student", student);
        return "graduationDesign/checkResult";
    }

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
        Label label = labelService.selectById(Integer.parseInt(type));
        labelService.updateLabel(label.getCourseNumber()+","+labelNumber,label.getId());
        return "redirect:/graduationDesign/declare";
    }

    @GetMapping("chooseStudent")
    public String chooseStudent(String studentNumber, String serialnumber) {
        subjectResultsService.insert(studentNumber, serialnumber);
        int number = graduationSubjectService.getSubject(serialnumber).getNumber();
        int max = graduationSubjectService.getSubject(serialnumber).getMax();
        if(number<max) {
           graduationSubjectService.updateNumber(number+1, serialnumber);
        }
        return "redirect:/graduationDesign/detail";
    }

    @PostMapping("subjectChange")
    public String subjectChange(String serialnumber, String name, String type, String introduce) {
//        System.out.println(name);
//        System.out.println(serialnumber);
//        System.out.println(type);
//        System.out.println(introduce);
        graduationSubjectService.updateContent(name, introduce, serialnumber);
        Label label = labelService.selectById(Integer.parseInt(type));
        if(label.getCourseNumber().contains(serialnumber)) {

        }else{
            Label label2 = labelService.selectBySubject(serialnumber);
            System.out.println("qwdqwfqw"+label2.getCourseNumber());
            String[] courseNumber = label2.getCourseNumber().split(",");
            ArrayList<String> list = new ArrayList<String>();
            for(String val:courseNumber) {
//                System.out.println(val);
//                System.out.println(val.equals(serialnumber));
                if(!(val.equals(serialnumber))) {
//                    System.out.println(val);
                    list.add(val);
                }
            }
            String[] newCourseNumber = list.toArray(new String[list.size()]);
            String str = StringUtils.join(newCourseNumber, ",");
//            System.out.println(str);
            labelService.updateLabel(str, label2.getId());
            labelService.updateLabel(label.getCourseNumber()+","+serialnumber, label.getId());
        }
        return "redirect:/graduationDesign/detail";
    }

    @GetMapping("select")
    public String test(ModelMap modelMap) {
        GraduationSubject graduationSubject = graduationSubjectService.getSubject("001");
        modelMap.addAttribute("graduationSubject", graduationSubject);
        return "graduationDesign/test";
    }

    @GetMapping("mystudent")
    public String mystudent(Model model) {
        String number = cookiesService.getCookies("username");
        GraduationSubject subject = graduationSubjectService.selectByNumber(number);
        SubjectResults[] subjectResults = subjectResultsService.selectBySerialnumber(subject.getSerialnumber());
        model.addAttribute("result", subjectResults);
        List<String> list = new ArrayList<String>();
        for(SubjectResults val:subjectResults) {
            list.add(studentService.selectStudent(val.getStudentNumber()).getName());
        }
        String[] name = list.toArray(new String[list.size()]);
//        System.out.println(name);
//        for(String val:name) {
//            System.out.println(val);
//        }
        model.addAttribute("name", name);
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
    public String appraise(String studentNumber, Model model) {
        Student student = studentService.selectStudent(studentNumber);
        SubjectResults subjectResults = subjectResultsService.selectByStudent(studentNumber);
        String course = subjectResults.getCourseNumber();
        GraduationSubject graduationSubject = graduationSubjectService.getSubject(course);
        model.addAttribute("subject", graduationSubject);
        System.out.println(graduationSubject.getSerialnumber());
        String label = labelService.selectBySubject(graduationSubject.getSerialnumber()).getName();
        model.addAttribute("label", label);
        model.addAttribute("student", student);
        model.addAttribute("result", subjectResults);
        return "graduationDesign/appraise";
    }
    @GetMapping("detail")
    public String detail(Model model) {
        String number = cookiesService.getCookies("username");
        System.out.println(number);
        GraduationSubject graduationSubject = graduationSubjectService.selectByNumber(number);
//        System.out.println("awefaefw"+graduationSubject.getSerialnumber());
        model.addAttribute("teacherNumber", number);
        model.addAttribute("subject", graduationSubject);
        Label label = labelService.selectBySubject(graduationSubject.getSerialnumber());
        model.addAttribute("label", label.getName());
        System.out.println(label.getName());
        Label[] labels = labelService.getAllLabel();
        model.addAttribute("allLabels", labels);
//        ChooseSubject[] chooseSubjects = chooseSubjectService.selectChoose(graduationSubject.getSerialnumber());
        String[] studentNumber = chooseSubjectService.selectStudent(graduationSubject.getSerialnumber());
        ArrayList<Student> students = new ArrayList<Student>();
        for(String val:studentNumber) {
            students.add(studentService.selectStudent(val));
        }
        Student[] newArray=students.toArray(new Student[students.size()]);
        model.addAttribute("students", newArray);
        return "graduationDesign/Subdetail-teacher";
    }
    @GetMapping("subject")
    public String subject() {
        return "graduationDesign/showSubject";
    }
}
