package com.jxd.stuManger.vo;

import com.jxd.stuManger.model.SelectedCourse;

/**
 * @author 何硕
 * @version 1.0
 * @className CourseWithSCourse
 * @description TODO
 * @date 2025/9/15 0:38
 */
public class CourseWithSCourse {
    private int courseId;
    private String courseName;
    private int teacherId;
    private String courseTime;
    private String classroom;
    private int classWeek;
    private String type;
    private int collegeId;
    private int score;
    private SelectedCourse selectedCourse;

    public CourseWithSCourse() {
    }

    public CourseWithSCourse(int courseId, String courseName, int teacherId, String courseTime, String classroom, int classWeek, String type, int collegeId, int score, SelectedCourse selectedCourse) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacherId = teacherId;
        this.courseTime = courseTime;
        this.classroom = classroom;
        this.classWeek = classWeek;
        this.type = type;
        this.collegeId = collegeId;
        this.score = score;
        this.selectedCourse = selectedCourse;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public int getClassWeek() {
        return classWeek;
    }

    public void setClassWeek(int classWeek) {
        this.classWeek = classWeek;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public SelectedCourse getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(SelectedCourse selectedCourse) {
        this.selectedCourse = selectedCourse;
    }
}
