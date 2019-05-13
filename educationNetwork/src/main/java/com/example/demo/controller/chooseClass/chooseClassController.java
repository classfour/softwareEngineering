package com.example.demo.controller.chooseClass;

import com.example.demo.domain.ChooseClass;
import com.example.demo.domain.Course;
import com.example.demo.service.ChooseClassService;
import com.example.demo.service.CookiesService;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("chooseClass")

public class chooseClassController {
    @Autowired
    private CourseService iuser;
    @Autowired
    private CookiesService cookiesService;
    @Autowired
    private ChooseClassService cclass;
    @GetMapping("/index")
    public  String userList(Model model){
        String username = cookiesService.getCookies("username");
        System.out.println(username);
        List<String> numberOrderedList=iuser.selectnumber();
        System.out.println("到这里还是没有关系的");
        List<Integer> statusList =new ArrayList<>();
        for(String each:numberOrderedList){
            System.out.println(each);
        }
        for(String each:numberOrderedList){
            System.out.println(each);
            ChooseClass tempres=cclass.findstatus(username,each);
        if (tempres!=null){
            iuser.updateStatusByNumber(each,1);
        }
        else {
            iuser.updateStatusByNumber(each,0);
        }
        if(tempres==null){
            System.out.println("zhouzhou");
        }
        else{
            System.out.println("zhuzhu");
        }

        }
        model.addAttribute("contents",iuser.getUserByCardno());
        //选出学生选的课程的编号
        List<String> courseNumber=cclass.getCounumByStunum(username);
        List<Course> filter=new ArrayList<Course>();
        for(String each:courseNumber){
            System.out.println(each);
            filter.add(iuser.getCourseByNumber(each));
        }
        model.addAttribute("filtercontents",filter);

        //选出学生选课的时间表
        List<String> time=new ArrayList<String>();
//        String[] newlist=new String[]{"10000000000000","20000000000000","30000000000000","40000000000000","50000000000000","60000000000000","70000000000000"};
//
//        List list = Arrays.asList("10000000000000","20000000000000","30000000000000","40000000000000","50000000000000","60000000000000","70000000000000");
        time.add("10000000000000");
        time.add("20000000000000");
        time.add("30000000000000");
        time.add("40000000000000");
        time.add("50000000000000");
        time.add("60000000000000");
        time.add("70000000000000");

//        time.addAll(list);
        List<String> useroccupationlist=new ArrayList<String>();
        for(String each:courseNumber){
            String temp=iuser.getoccupation(each);
            System.out.println(temp);
            useroccupationlist.add(temp);
        }
        for(String each: useroccupationlist){
            System.out.printf(each);
            int day=each.charAt(0)-'0';
            System.out.printf("zhouzhou-day");
            System.out.println(day);
            //time.get(day)  10000000000000
            StringBuffer sourcestring=new StringBuffer(time.get(day-1));
            System.out.println(sourcestring);
            StringBuffer tempstring=new StringBuffer(each);
            StringBuffer newstring=new StringBuffer("");
            newstring.append(day);
            for(int i=1;i<=13;i++){
                newstring.append(Integer.parseInt(String.valueOf(sourcestring.charAt(i)))+Integer.parseInt(String.valueOf(tempstring.charAt(i))));
            }
            System.out.println(newstring);
            time.remove(day-1);
            time.add(day-1,newstring.toString());
        }
        Map<String,Integer> one=new LinkedHashMap<String,Integer>();
        for(int i=1;i<=13;i++){
//            String tempxinqi=time.get(i);
            one.put(""+('A'+i-1),time.get(1).charAt(i)-'0');
        }
        Set<String> keys= one.keySet();
        for(String key: keys){
            int value= one.get(key);
            System.out.println(key+"--"+value);
        }
        System.out.println(one.get("65"));
        System.out.println(one);

        model.addAttribute("occupationlist",one);
        System.out.print(time.get(0));
        return "/chooseClass/index";
    }

    @GetMapping(value = "/change/{number}")
    public String changestatus(@PathVariable("number") String number,int status){
        System.out.println(status);

        iuser.updateStatusByNumber(number,1-status);
        return "/chooseClass/index";
    }
    @GetMapping(value = "/change/{number}/{status}")
    public String changeStatus(@PathVariable("number") String number,@PathVariable("status") int status){
        System.out.println(status);

        iuser.updateStatusByNumber(number,1-status);
        return "/chooseClass/index";
    }
    @GetMapping(value = "/change}")
    public String change_status( String number,int status){
        System.out.println(status);
        iuser.updateStatusByNumber(number,1-status);
        return "chooseClass/index";
    }
    @ResponseBody
    @PostMapping("exit")
    public String exit(String number) {
//        Course tempobj=iuser.getCourseByNumber(number);
//        iuser.updateStatusByNumber(number,1-tempobj.getStatus());
////        String studentNumber = cookiesService.getCookies("username");
////        GraduationSubject graduationSubject = graduationSubjectService.getSubject(serialnumber);
////        graduationSubjectService.updateNwoNumber(graduationSubject.getNowNumber()-1, serialnumber);
////        return chooseSubjectService.delete(serialnumber, studentNumber);
//        return "true";

        System.out.println("hi");
        String username = cookiesService.getCookies("username");
        ChooseClass temp=new ChooseClass();
        temp.setCourse_number(number);
        temp.setStudent_number(username);
        if(cclass.getStudent_number(number)==username){
//            System.out.println("falsesfsfdafafafafsa");
        }
        else{
            System.out.println("successsuccesscufaefafaf");
            cclass.insertval(temp);
        }
        return "true";
    }

    @ResponseBody
    @PostMapping("retire")
    public String retire(String number) {
//        Course tempobj=iuser.getCourseByNumber(number);
//        iuser.updateStatusByNumber(number,1-tempobj.getStatus());
////        String studentNumber = cookiesService.getCookies("username");
////        GraduationSubject graduationSubject = graduationSubjectService.getSubject(serialnumber);
////        graduationSubjectService.updateNwoNumber(graduationSubject.getNowNumber()-1, serialnumber);
////        return chooseSubjectService.delete(serialnumber, studentNumber);
//        return "true";

        System.out.println("hi");
        String username = cookiesService.getCookies("username");
        cclass.deleteevent(username,number);
        return "true";
    }

}

