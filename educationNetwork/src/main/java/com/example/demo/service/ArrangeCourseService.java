package com.example.demo.service;


import java.util.List;
import java.util.Map;

public interface ArrangeCourseService {

    List<String> getUnarrangedCoursesBySpecialty(String specialty);

    Map<String, Object> getTeachersInfoTeachCourse(String courseName);

    int getCourseNumber(String courseName);

    String getAvailableClassroom(String course, String week, String detail);

    boolean updateClassroomOccupied(String week, String occpuied, String classNumber);

    boolean updateCourse(String week, String detail, String teacherName, String class_number, String course);

    String getArrangedCoursesOccupation(String specialty);

    boolean updateTeacherOccupied(String week, String occupied, String teacherName);
}
