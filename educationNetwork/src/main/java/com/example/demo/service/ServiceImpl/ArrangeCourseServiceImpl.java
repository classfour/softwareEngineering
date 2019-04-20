package com.example.demo.service.ServiceImpl;

import com.example.demo.domain.ArrangeCourseClassroomEntity;
import com.example.demo.domain.ArrangeCourseTeacherEntity;
import com.example.demo.mapper.ArrangeCourseMapper;
import com.example.demo.service.ArrangeCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArrangeCourseServiceImpl implements ArrangeCourseService {

    @Autowired
    private ArrangeCourseMapper arrangeCourseMapper;

    @Override
    public List<String> getUnarrangedCoursesBySpecialty(String specialty) {
        return arrangeCourseMapper.getUnarrangedCoursesBySpecialty(specialty);
    }

    @Override
    public Map<String, Object> getTeachersInfoTeachCourse(String courseName) {
        List<ArrangeCourseTeacherEntity> allTeachersCourses = arrangeCourseMapper.getAllTeachersInfo();
        List<String> getTeachers = new LinkedList<>();
        List<String> getTeachersOccupation = new LinkedList<>();
        int courseNumber = getCourseNumber(courseName);
        String courseNumberString = courseNumber + "";
        for (ArrangeCourseTeacherEntity i : allTeachersCourses) {
            String enable_teach_courses = i.getEnable_teach_courses();
            String[] s = enable_teach_courses.split(";");
            for (String c : s) {
                if (c.equals(courseNumberString)) {
                    getTeachers.add(i.getName());
                    getTeachersOccupation.add(i.getOccupation());
                    continue;
                }
            }
        }
        Map<String, Object> mp = new HashMap<>();
        mp.put("teachers", getTeachers);
        mp.put("occupations", getTeachersOccupation);
        return mp;
    }

    @Override
    public int getCourseNumber(String courseName) {
        return arrangeCourseMapper.getCourseNumber(courseName);
    }

    @Override
    public String getAvailableClassroom(String course, String week, String detail) {
        String[] details = detail.split("-");
        int start = Integer.parseInt(details[0]) - 1;
        int end = Integer.parseInt(details[1]) - 1;
        int weekInt = Integer.parseInt(week) - 1;
        int courseMaxNumber = arrangeCourseMapper.getCourseMaxNumber(course);
        //筛选出教室容量够用的教室
        List<ArrangeCourseClassroomEntity> classroomEntities = arrangeCourseMapper.getAvailableClassroomsInfo(courseMaxNumber);
        List<String> classroomNameResult = new ArrayList<>();
        for (ArrangeCourseClassroomEntity classroom : classroomEntities) {
            String[] occupied = classroom.getOccupied().split(";");
            //指定星期的占用情况
            String occ = occupied[weekInt];
            boolean flag = true;
            for (int i = start; i <= end; i++) {
                if (occ.charAt(i) == '1') {
                    flag = false;
                    break;
                }
            }
            if (flag == true) {
                classroomNameResult.add(classroom.getClass_number());
            }
        }
        int size = classroomNameResult.size();
        if (size != 0) {
            int random = (int) (Math.random() * size);
            String name = classroomNameResult.get(random);
            return name;
        } else {
            return null;
        }
    }

    @Override
    public boolean updateClassroomOccupied(String week, String occupied, String classNumber) {
        //所选择的教室的占用情况
        String occ = arrangeCourseMapper.getClassroomOccupied(classNumber);
        String[] detail = occupied.split("-");
        int weekInt = Integer.parseInt(week);
        int start = Integer.parseInt(detail[0]);
        int end = Integer.parseInt(detail[1]);
        StringBuilder stringBuilder = new StringBuilder(occ);
        for (int i = start; i <= end; i++) {
            stringBuilder.setCharAt((weekInt - 1) * 14 + i - 1, '1');
        }
        String res = stringBuilder.toString();
        return arrangeCourseMapper.updateClassroomOccupied(res, classNumber);
    }

    @Override
    public boolean updateCourse(String week, String detail, String teacherName, String class_number, String course) {
        String[] details = detail.split("-");
        int weekInt = Integer.parseInt(week);
        int start = Integer.parseInt(details[0]);
        int end = Integer.parseInt(details[1]);
        int teacherNumber = arrangeCourseMapper.getTeacherNumberByName(teacherName);
        StringBuilder stringBuilder = new StringBuilder("0000000000000");
        for (int i = start; i <= end; i++) {
            stringBuilder.setCharAt(i - 1, '1');
        }
        String occupation = weekInt + "" + stringBuilder.toString();
        return arrangeCourseMapper.updateCourse(teacherName + "", occupation, class_number, course);
    }

    @Override
    public String getArrangedCoursesOccupation(String specialty) {
        List<String> occupations = arrangeCourseMapper.getArrangedCoursesOccupation(specialty);
        StringBuilder[] occupation = {new StringBuilder("0000000000000"), new StringBuilder("0000000000000"), new StringBuilder("0000000000000"), new StringBuilder("0000000000000"), new StringBuilder("0000000000000"), new StringBuilder("0000000000000"), new StringBuilder("0000000000000")};

        for (String s : occupations) {
            if (!s.equals("")) {
                int week = s.charAt(0) - 48;
                for (int i = 1; i <= 13; i++) {
                    if (s.charAt(i) == '1') {
                        occupation[week - 1].setCharAt(i - 1, '1');
                    }
                }
            }
        }

        String res = "";
        for (int i = 0; i < 7; i++) {
            res += occupation[i].toString();
        }
        return res;
    }

}
