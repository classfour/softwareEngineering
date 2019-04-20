package com.example.demo.controller;

import com.example.demo.domain.ArrangeCourseAllInfo;
import com.example.demo.domain.ArrangeCourseTeacherEntity;
import com.example.demo.domain.ArrangeCourseTimeEntity;
import com.example.demo.service.ArrangeCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/arrangeCourse")
public class ArrangeCourseController {
    @Autowired
    private ArrangeCourseService arrangeCourseService;

    @RequestMapping("getcoursesbyspecialty")
    public Object getAllCoursesBySpecialty(HttpServletRequest httpServletRequest) {
        String specialty = httpServletRequest.getParameter("specialty");
        List<String> res = arrangeCourseService.getAllCoursesBySpecialty(specialty);
        Map<String, Object> mp = new HashMap<>();
        mp.put("courses", res);
        return mp;
    }

    @RequestMapping("getteachersinfoteachcourse")
    public Object getTeachersInfoTeachCourse(HttpServletRequest httpServletRequest) {
        String course = httpServletRequest.getParameter("course");
        return arrangeCourseService.getTeachersInfoTeachCourse(course);
    }


    @RequestMapping(value = "availableclassroom", method = {RequestMethod.POST})
    public Object getAvailableClassroom(@RequestBody ArrangeCourseTimeEntity arrangeCourseTimeEntity) {
        String week = arrangeCourseTimeEntity.getWeek();
        String detail = arrangeCourseTimeEntity.getDetail();
        String course = arrangeCourseTimeEntity.getCourse();
        String class_number = arrangeCourseService.getAvailableClassroom(course, week, detail);
        Map<String, Object> mp = new HashMap<>();
        if (class_number == null) {
            mp.put("result", "fail");
        } else {
            mp.put("result", "success");
            mp.put("class_number", class_number);
        }
        return mp;
    }

    @RequestMapping("updateAllInfo")
    public Object updateClassroomOccupied(@RequestBody ArrangeCourseAllInfo arrangeCourseAllInfo) {
        String week = arrangeCourseAllInfo.getWeek();
        String occupied = arrangeCourseAllInfo.getDetail();
        String classNumber = arrangeCourseAllInfo.getClass_number();
        boolean result = arrangeCourseService.updateClassroomOccupied(week, occupied, classNumber);
        return null;
    }
}
