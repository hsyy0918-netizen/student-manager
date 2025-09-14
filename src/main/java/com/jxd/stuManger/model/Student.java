package com.jxd.stuManger.model;
public class Student {
    private int studentId;
    private String studentName;
    private String sex;
    private String birthday;
    private String grade;
    private int collegeId;


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

    public String getSex() {
        return sex;
    }
    @Override
    public String toString() {
        return "Student{" +
            "studentId=" + studentId +
            ", studentName='" + studentName + '\'' +
            ", sex='" + sex + '\'' +
            ", birthday='" + birthday + '\'' +
            ", grade='" + grade + '\'' +
            ", collegeId=" + collegeId +
            '}';
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }
    public Student() {
    }

    public Student(int studentId, String studentName, String sex, String birthday, String grade, int collegeId) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.sex = sex;
        this.birthday = birthday;
        this.grade = grade;
        this.collegeId = collegeId;
    }
}