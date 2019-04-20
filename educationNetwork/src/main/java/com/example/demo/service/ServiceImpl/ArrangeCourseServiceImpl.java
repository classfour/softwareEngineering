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
    public List<String> getAllCoursesBySpecialty(String specialty) {
        return arrangeCourseMapper.getAllCoursesBySpecialty(specialty);
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

}
