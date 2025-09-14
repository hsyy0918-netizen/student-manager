package com.jxd.stuManger.model;

public class SelectedCourse {
    private int courseId;
    private int StudentId;
    private int mark;

    public SelectedCourse(int courseId) {

    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public SelectedCourse(int courseId, int studentId, int mark) {
        this.courseId = courseId;
        StudentId = studentId;
        this.mark = mark;
    }
}