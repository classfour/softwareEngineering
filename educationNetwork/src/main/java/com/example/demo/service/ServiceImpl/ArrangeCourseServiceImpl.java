package com.example.demo.service.ServiceImpl;

import com.example.demo.domain.ArrangeCourseTeacherEntity;
import com.example.demo.mapper.ArrangeCourseMapper;
import com.example.demo.service.ArrangeCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

}
