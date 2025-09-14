package com.jxd.stuManger.model;

/**
 * 课程信息实体类（对应表：course）
 */
public class Course  {
    private int courseId;
    private String courseName;
    private int teacherId;
    private String courseTime;
    private String classroom;
    private int classWeek;
    private String type;
    private int collegeId;
    private int score;

    public Course() {

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

    public Course(int courseId, String courseName, int teacherId, String courseTime, String classroom, int classWeek, String type, int collegeId, int score) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacherId = teacherId;
        this.courseTime = courseTime;
        this.classroom = classroom;
        this.classWeek = classWeek;
        this.type = type;
        this.collegeId = collegeId;
        this.score = score;
    }
}