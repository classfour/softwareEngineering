package com.example.demo.domain;

public class SubjectResults {
    private String student_number;

    private String course_number;

    private String title;

    private String content;

    private int result;

    private int status;

    private String evaluate;

    public String getStudentNumber() {
        return student_number;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public void setStudentNumber(String studentNumber) {
        this.student_number = studentNumber;
    }

    public String getCourseNumber() {
        return course_number;
    }

    public void setCourseNumber(String courseNumber) {
        this.course_number = courseNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
