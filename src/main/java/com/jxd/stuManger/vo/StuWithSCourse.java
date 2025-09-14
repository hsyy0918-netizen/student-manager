package com.jxd.stuManger.vo;

import com.jxd.stuManger.model.SelectedCourse;

/**
 * @author 何硕
 * @version 1.0
 * @className StuWithSCourse
 * @description TODO
 * @date 2025/9/14 21:49
 */
public class StuWithSCourse {
    private int studentId;
    private String studentName;
    private SelectedCourse selectedCourse;

    public StuWithSCourse(int studentId, String studentName, SelectedCourse selectedCourse) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.selectedCourse = selectedCourse;
    }

    public StuWithSCourse() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public SelectedCourse getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(SelectedCourse selectedCourse) {
        this.selectedCourse = selectedCourse;
    }
}
