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
import java.lang.reflect.Array;
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
    @Autowired
    private TeacherService teacherService;

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
        if(subjectResults==null) {
            return "graduationDesign/checkResult";
        }
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

    @ResponseBody
    @PostMapping("complain")
    public boolean complain(String courseNumber) {
        String studentNumber = cookiesService.getCookies("username");
        return subjectResultsService.updateStatus(1,studentNumber);
    }

    @PostMapping("contentSubmit")
    public String contetnSubmit(String title, String content) {
        String studentNumber = cookiesService.getCookies("username");
        subjectResultsService.submit(title, content, studentNumber);
        return "redirect:/graduationDesign/content";
    }

    @GetMapping("content")
    public String content(Model model) {
        String studentNumber = cookiesService.getCookies("username");
        SubjectResults subjectResults = subjectResultsService.selectByStudent(studentNumber);
        if(subjectResults == null) {
            model.addAttribute("msg", "您暂未选择任何课题");
            model.addAttribute("url", "/index");
            return "graduationDesign/error";
        }
        model.addAttribute("result", subjectResults);
        return "graduationDesign/content";
    }

    @ResponseBody
    @PostMapping("labelChoose")
    public GraduationSubject[] labelChoose(String chooseDepartment, String chooseLabel,boolean remain) {
//        System.out.println(StringUtils.join(number, ","));
        System.out.println(remain);
        String labelStr;
        GraduationSubject[] graduationSubjects = graduationSubjectService.selectAll();
        List<String> allLabel = new ArrayList<String>();
        for(GraduationSubject subject:graduationSubjects) {
            if(remain) {
                if(subject.getNowNumber()<subject.getMaxNumber() && subject.getNumber() < subject.getMax()) {
                    allLabel.add(subject.getSerialnumber());
                }
            }else{
                allLabel.add(subject.getSerialnumber());
            }
        }
        String[] number = allLabel.toArray(new String[allLabel.size()]);
        labelStr = StringUtils.join(number, ",");
//        System.out.println("name="+name);
        if(!chooseLabel.equals("")) {
            String[] label = labelService.getSubjectNumber(chooseLabel, number);
            number = null;
            number = (String[])label.clone();
        }

        if(!chooseDepartment.equals("")) {
            String[] label = labelService.selectByDepartment(chooseDepartment, number);
            number = null;
            number = (String[])label.clone();
        }

        labelStr = StringUtils.join(number, ",");
        List<GraduationSubject> graduationSubjectsArray = new ArrayList<GraduationSubject>();
        for(String value:number) {
            GraduationSubject graduationSubject = graduationSubjectService.getSubject(value);
            graduationSubject.setTeacher(teacherService.selectByNumber(graduationSubject.getTeacherNumber()).getName());
            graduationSubjectsArray.add(graduationSubject);
            System.out.println("hello"+graduationSubject.getTeacher());
        }
        GraduationSubject[] newSubjects = graduationSubjectsArray.toArray(new GraduationSubject[graduationSubjectsArray.size()]);
        return newSubjects;
    }

    @ResponseBody
    @PostMapping("chooseSubject")
    public boolean chooseSubject(String serialnumber) {
        System.out.println(serialnumber);
        String studentNumber = cookiesService.getCookies("username");
        GraduationSubject graduationSubject = graduationSubjectService.getSubject(serialnumber);
        if(graduationSubject.getMax() == graduationSubject.getNumber() || graduationSubject.getMaxNumber() == graduationSubject.getNowNumber()) {
            return false;
        }
        ChooseSubject chooseSubject = new ChooseSubject();
        chooseSubject.setStudentNumber(studentNumber);
        chooseSubject.setCourseNumber(serialnumber);
        chooseSubjectService.insertChoose(chooseSubject);
        return graduationSubjectService.updateNwoNumber(graduationSubject.getNowNumber()+1, serialnumber);
    }

    @ResponseBody
    @PostMapping("exit")
    public boolean exit(String serialnumber) {
        String studentNumber = cookiesService.getCookies("username");
        GraduationSubject graduationSubject = graduationSubjectService.getSubject(serialnumber);
        graduationSubjectService.updateNwoNumber(graduationSubject.getNowNumber()-1, serialnumber);
        return chooseSubjectService.delete(serialnumber, studentNumber);
    }

    @GetMapping("selectSubject")
    public String selectSubject(Model model) {
        String studentNumber = cookiesService.getCookies("username");
        SubjectResults subjectResults = subjectResultsService.selectByStudent(studentNumber);
        if(subjectResults!=null) {
            model.addAttribute("confirm", subjectResults.getCourseNumber());
        }
        System.out.println(studentNumber);
        ChooseSubject[] chooseSubjects = chooseSubjectService.selectByStudent(studentNumber);
        if(chooseSubjects != null) {
            System.out.println(chooseSubjects);
            List<GraduationSubject> alreadyChoose = new ArrayList<GraduationSubject>();
            for(ChooseSubject choose:chooseSubjects) {
                System.out.println(choose.getCourseNumber());
                GraduationSubject temp = graduationSubjectService.getSubject(choose.getCourseNumber());
                temp.setTeacher(teacherService.selectByNumber(temp.getTeacherNumber()).getName());
                alreadyChoose.add(temp);
            }
            GraduationSubject[] chooseArray = alreadyChoose.toArray(new GraduationSubject[alreadyChoose.size()]);
            model.addAttribute("choose", chooseArray);
        }
        GraduationSubject[] graduationSubjects = graduationSubjectService.selectAll();
        model.addAttribute("subjects", graduationSubjects);
        List<String> teachers = new ArrayList<String>();
        for(GraduationSubject subject:graduationSubjects) {
            String name = teacherService.selectByNumber(subject.getTeacherNumber()).getName();
            teachers.add(name);
        }
        String[] name = teachers.toArray(new String[teachers.size()]);
        Label[] labels = labelService.getAllLabel();
        model.addAttribute("labels", labels);
        model.addAttribute("name", name);
        return "graduationDesign/selectSubject";
    }



    @GetMapping("chooseStudent")
    public String chooseStudent(String studentNumber, String serialnumber) {
        subjectResultsService.insert(studentNumber, serialnumber);
        int number = graduationSubjectService.getSubject(serialnumber).getNumber();
        int max = graduationSubjectService.getSubject(serialnumber).getMax();
        studentService.updateStatus(1, studentNumber);
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
        if(subject==null)
            return "graduationDesign/mystudent";
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
//        System.out.println(number);
        GraduationSubject graduationSubject = graduationSubjectService.selectByNumber(number);
//        System.out.println("awefaefw"+graduationSubject.getSerialnumber());
        if(graduationSubject==null){
//            System.out.println("awejfoawjeiajfeajwefioj");
            return "redirect:/login";
        }

        model.addAttribute("teacherNumber", number);
        model.addAttribute("subject", graduationSubject);
        Label label = labelService.selectBySubject(graduationSubject.getSerialnumber());
        model.addAttribute("label", label.getName());
        System.out.println(label.getName());
        Label[] labels = labelService.getAllLabel();
        model.addAttribute("allLabels", labels);
//        ChooseSubject[] chooseSubjects = chooseSubjectService.selectChoose(graduationSubject.getSerialnumber());
        String[] studentNumber = chooseSubjectService.selectStudent(graduationSubject.getSerialnumber());
        if(studentNumber==null) {
            model.addAttribute("students", null);
        }else{
            ArrayList<Student> students = new ArrayList<Student>();
            for(String val:studentNumber) {
                Student student = studentService.selectStudent(val);
                if(student.getStatus()==0)
                    students.add(student);
            }
            Student[] newArray=students.toArray(new Student[students.size()]);
            model.addAttribute("students", newArray);
        }

        return "graduationDesign/Subdetail-teacher";
    }
}
