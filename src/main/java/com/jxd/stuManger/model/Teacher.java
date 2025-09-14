package com.jxd.stuManger.model;

/**
 * 教师信息实体类（对应表：teacher）
 */
public class Teacher {
    private int teacherId;
    private String teacherName;
    private String sex;
    private String birthday;
    private String degree;
    private String title;
    private String hireDate;
    private int collegeId;

    public Teacher(int teacherId, String teacherName, String sex, String birthday, String degree, String title, String hireDate, int collegeId) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.sex = sex;
        this.birthday = birthday;
        this.degree = degree;
        this.title = title;
        this.hireDate = hireDate;
        this.collegeId = collegeId;
    }
    public Teacher() {

    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }
}