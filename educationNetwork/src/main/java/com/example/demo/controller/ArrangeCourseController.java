package com.example.demo.controller;

import com.example.demo.domain.ArrangeCourseTeacherEntity;
import com.example.demo.service.ArrangeCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
