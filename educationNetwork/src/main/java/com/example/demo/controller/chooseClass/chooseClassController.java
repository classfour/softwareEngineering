package com.example.demo.controller.chooseClass;

import com.example.demo.domain.ChooseClass;
import com.example.demo.domain.Course_for_choose_class__;
import com.example.demo.domain.Student_for_choose_class__;
import com.example.demo.service.ChooseClassService;
import com.example.demo.service.CookiesService;
import com.example.demo.service.CourseService_for_choose_class;
import com.example.demo.service.StudentService_for_choose_class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping("chooseClass")

public class chooseClassController {
    @Autowired
    private CourseService_for_choose_class iuser;
    @Autowired
    private CookiesService cookiesService;
    @Autowired
    private ChooseClassService cclass;
    @Autowired
    private StudentService_for_choose_class stu;
    @GetMapping("/index")
    public  String userList(Model model){
        String username = cookiesService.getCookies("username");
        System.out.println(username);
        //输出所有课程表的课程编号
        List<String> numberOrderedList=iuser.selectnumber();
        System.out.println("到这里还是没有关系的");
        List<Integer> statusList =new ArrayList<>();
        for(String each:numberOrderedList){
            System.out.println(each);
        }
        //如果在选课表中显示了这个同学的选课记录-显示
        //numberOrderList  --所有课程表的编号
        //username-用户学号
        //选出有没有学号和课程编号对应的选课记录，如有的话修改这个课程的选课状态为
        //如果选课标表中没有就添加，如果有的话就修改，删除掉这个表的选课状态字段

        //首先是课程状态字段的显示
        //思路：根据用户工号与选课编号定位到选课表中的记录，显示这条记录的status字段
        //选出学生选的课程的编号
        List<String> courseNumber=cclass.getCounumByStunum(username);
        List<Course_for_choose_class__> eventshow=new ArrayList<Course_for_choose_class__>();
        eventshow.addAll(cclass.getStatus(username));
        List<Course_for_choose_class__> allshow=new ArrayList<Course_for_choose_class__>();
        allshow.addAll(cclass.getAll(username));
//        for(Course_for_choose_class__ each:eventshow){
//            System.out.println("ZHOUZHOU"+each.getTeachername());
//        }

        //add---------------------------------------------------------------
        int times=0;
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "周一");map.put("2", "周二");map.put("3", "周三");map.put("4", "周四");map.put("5", "周五");
        for (Course_for_choose_class__ temp__:eventshow) {
            String temp_oc =temp__.getOccupation();
            System.out.println(temp_oc);
            //          temp__.setOccupation(return_shangkeshijian);
            if(temp_oc.length()==0  || temp_oc==null) continue;
            //  System.out.println(temp_oc);
            char[] c = temp_oc.toCharArray();
            String zhouji_ = c[0]+"";
            String zhouji = map.get(zhouji_);
            String return_shangkeshijian = zhouji;
            for(int i=1;i<14;i++)
            {
                if(c[i]!='0')
                {
                    return_shangkeshijian=return_shangkeshijian+","+i;
                }
            }
            if(temp__.getType().equals("0") ) { temp__.setType("非实验课");  } else {   temp__.setType("实验课");   }
            //System.out.println(return_shangkeshijian);
            temp__.setOccupation(return_shangkeshijian);
        }
        //add---------------------------------------------------------------

        //add---------------------------------------------------------------
        times=0;
        map.put("1", "周一");map.put("2", "周二");map.put("3", "周三");map.put("4", "周四");map.put("5", "周五");
        for (Course_for_choose_class__ temp__:allshow) {
            String temp_oc =temp__.getOccupation();

            //          temp__.setOccupation(return_shangkeshijian);
            if(temp_oc.length()==0  || temp_oc==null) continue;

            char[] c = temp_oc.toCharArray();
            String zhouji_ = c[0]+"";
            String zhouji = map.get(zhouji_);
            String return_shangkeshijian = zhouji;
            for(int i=1;i<14;i++)
            {
                if(c[i]!='0')
                {
                    return_shangkeshijian=return_shangkeshijian+","+i;
                }
            }
            //System.out.println(return_shangkeshijian);
            if(temp__.getType().equals("0") ) { temp__.setType("非实验课");  } else {   temp__.setType("实验课");   }
            temp__.setOccupation(return_shangkeshijian);
        }
        //add---------------------------------------------------------------
       // System.out.println("zhouzhoutestzhouzhouzhou"+eventshow.size());
        model.addAttribute("filtercontents",eventshow);
        model.addAttribute("contents",allshow);

        return "/chooseClass/index";
    }
    @GetMapping(value = "/change}")
    public String change_status( String number,int status){
      //  System.out.println(status);
        iuser.updateStatusByNumber(number,1-status);
        return "chooseClass/index";
    }

    @ResponseBody
    @PostMapping("exit")
    public String exit(String number) {

        System.out.println("hi");
        String username = cookiesService.getCookies("username");
        System.out.println(username);
       // System.out.println(number);
        Student_for_choose_class__ student_forchooseclass_informatioin = stu.selectStudent(username);
        String get_student_course_ou  = student_forchooseclass_informatioin.getSchedule();    //0000000 98位
       // System.out.println("zhohzoutestzhouzhou");
       // System.out.println(student_forchooseclass_informatioin);
       // System.out.println("zhohzoutestzhouzhou");
        System.out.println(  "zhouzhououtput"+ student_forchooseclass_informatioin.getNumber() );

        Course_for_choose_class__ getcourse=iuser.getCourseByNumber(number);
        String ou =  getcourse.getOccupation();     //00000000  14位
        System.out.println(ou);
        System.out.println(get_student_course_ou);
        String temp_for_ou_course[] = ou.split("");     //0000 14位置
        String temp_for_student[] = get_student_course_ou.split("");    //98位
        System.out.println(temp_for_ou_course.length);
        System.out.println(temp_for_student.length);
        System.out.println(temp_for_ou_course[0]);
        int first_pos  = Integer.parseInt   (temp_for_ou_course[0]);
        first_pos = (first_pos-1)*14;


        System.out.println(first_pos);   //比如 1 111 0000000000 这里的fisrt_pos 就是第一个 1  即使星期几
        //数组下标 first_pos --
        //为了契合数据下标      这里改成0  因为对于98位的数组下标开头是0 10000000000200000000
        for(int i=1;i<14;i++)
        {
            System.out.println("first_pos"+ first_pos+"res: "+temp_for_student[first_pos+i]+" i "+i+" res:"+ temp_for_ou_course[i]);
            if( temp_for_student[first_pos+i].equals("1") && temp_for_ou_course[i].equals("1"))
            {
                System.out.println("false");
                return "false";
            }
        }
        for(int i=0;i<14;i++)
        {
            temp_for_student[first_pos+i]= temp_for_ou_course[i];
        }
        String res = String.join("", temp_for_student);
        System.out.println(res);

        stu.changeSchedule(username,res);


        if(cclass.findChooseClass(username,number)==null){
            ChooseClass temp=new ChooseClass();
            temp.setCourse_number(number);
            temp.setStudent_number(username);
            cclass.insertval(temp);
            cclass.changeStatusone(username,number);
            Course_for_choose_class__ obj=iuser.getCourseByNumber(number);
            iuser.upadtePeopleByNumber(obj.getPeople()+1,number);
        }
        else{
            Course_for_choose_class__ obj=iuser.getCourseByNumber(number);
            iuser.upadtePeopleByNumber(obj.getPeople()+1,number);
            cclass.changeStatusone(username,number);
        }



        return "true";
    }

    @ResponseBody
    @PostMapping("retire")
    public String retire(String number) {
        System.out.println("hi");
        String username = cookiesService.getCookies("username");
        cclass.changeStatus(username,number);
        Course_for_choose_class__ obj=iuser.getCourseByNumber(number);
        iuser.upadtePeopleByNumber(obj.getPeople()-1,number);


        Student_for_choose_class__ student_forchooseclass_informatioin = stu.selectStudent(username);
        String get_student_course_ou  = student_forchooseclass_informatioin.getSchedule();    //0000000 98位
        Course_for_choose_class__ getcourse=iuser.getCourseByNumber(number);
        String ou =  getcourse.getOccupation();     //00000000  14位

        String temp_for_ou_course[] = ou.split("");     //0000 14位置
        String temp_for_student[] = get_student_course_ou.split("");    //98位
        System.out.println(temp_for_ou_course.length);
        System.out.println(temp_for_student.length);
        System.out.println(temp_for_ou_course[0]);
        int first_pos  = Integer.parseInt   (temp_for_ou_course[0]);
        first_pos = (first_pos-1)*14;
        System.out.println(first_pos);   //比如 1 111 0000000000 这里的fisrt_pos 就是第一个 1  即使星期几
        //为了契合数据下标      这里改成0  因为对于98位的数组下标开头是0 10000000000200000000
        for(int i=1;i<14;i++)
        {
            System.out.println("first_pos"+ first_pos+"res: "+temp_for_student[first_pos+i]+" i "+i+" res:"+ temp_for_ou_course[i]);
            if( temp_for_student[first_pos+i].equals("1") && temp_for_ou_course[i].equals("1"))
            {
                temp_for_student[first_pos+i]="0";
            }
        }
        String res = String.join("", temp_for_student);
        System.out.println(res);

        stu.changeSchedule(username,res);
        return "true";
    }

    @ResponseBody
    @PostMapping("search")
    public Course_for_choose_class__[] search(@RequestBody String temp) throws UnsupportedEncodingException {

        System.out.println("hhh");
        String try_to_search_sql_temp[]  =  new String[6];
        String try_to_search_sql[]  =  new String[6];
        for(int i=0;i<6;i++)
        {
            try_to_search_sql[i]="";
        }
        //1     年纪      2020 grade_201x
        //2     专业      环境工程    major
        //3     开设学院    化学工程学院  departments
        //4     课程类别    创新创业教育  ctype
        //5     有无课程余量      有   people

        String result = java.net.URLDecoder.decode(temp,"utf-8");
        try_to_search_sql_temp = result.split(",");
        System.out.println(result);
        for(int i=0;i<try_to_search_sql_temp.length;i++)
        {
            if(try_to_search_sql_temp[i]!=null)
            {
                try_to_search_sql[i]=try_to_search_sql_temp[i];
            }
        }
        if(  try_to_search_sql[4].equals("非实验课")   )
            try_to_search_sql[4]="0";
        else if(try_to_search_sql[4].equals("实验课")) {

            try_to_search_sql[4] = "1";
        }
        if(  try_to_search_sql[5].equals("无")   )
            try_to_search_sql[5]="o";
        else  {

            try_to_search_sql[5] = "1";
        }
        for(int i=0;i<6;i++)
        {
            System.out.print(try_to_search_sql[i]+"//");
        }
        /*String grade_201x = try_to_search_sql[1];
        String major = try_to_search_sql[2];*/
        System.out.println("1"+try_to_search_sql[1]);
        System.out.println("2"+try_to_search_sql[2]);
        Course_for_choose_class__ res [] = new Course_for_choose_class__[500];
        // res=Course_search_service.find(try_to_search_sql[1],try_to_search_sql[2],try_to_search_sql[3],try_to_search_sql[4],try_to_search_sql[5]);
        res=iuser.selectCourseRepaint(try_to_search_sql[1],try_to_search_sql[2],try_to_search_sql[3],try_to_search_sql[4],try_to_search_sql[5]);
        Course_for_choose_class__[] end= new Course_for_choose_class__[res.length];


        int times=0;
//        for(Course_for_choose_class__ each:res){
//            end[times++]=cclass.getsingle(each.getNumber());
//        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "周一");map.put("2", "周二");map.put("3", "周三");map.put("4", "周四");map.put("5", "周五");
        //System.out.println("innnertest-"+times);
        System.out.println("zhouzhoutest526");
        for(Course_for_choose_class__ each:res){
            System.out.println(each.getNumber());
        }
        System.out.println("zhouzhoutest526");
        for(Course_for_choose_class__ each:res){

            end[times]=cclass.getsingle(each.getNumber());
            times++;
        }

//        for(int ii=0;ii<end.length;ii++)
//        {
        for (Course_for_choose_class__ temp__:
                end) {
            String temp_oc =temp__.getOccupation();
            System.out.println(temp_oc);
            //          temp__.setOccupation(return_shangkeshijian);
            if(temp_oc.length()==0  || temp_oc==null) continue;
            //  System.out.println(temp_oc);
            char[] c = temp_oc.toCharArray();
            String zhouji_ = c[0]+"";
            String zhouji = map.get(zhouji_);
            String return_shangkeshijian = zhouji;
            for(int i=1;i<14;i++)
            {
                if(c[i]!='0')
                {
                    return_shangkeshijian=return_shangkeshijian+","+i;
                }
            }
            if(temp__.getType().equals("0") ) { temp__.setType("非实验课");  } else {   temp__.setType("实验课");   }
            //System.out.println(return_shangkeshijian);
            temp__.setOccupation(return_shangkeshijian);
        }

        return end;
    }

}

