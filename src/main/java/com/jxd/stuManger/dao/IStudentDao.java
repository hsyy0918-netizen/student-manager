package com.jxd.stuManger.dao;

import com.jxd.stuManger.model.Student;

import java.util.*;

public interface IStudentDao {
    //学生管理
    int insertStudent(Student student);
    boolean delStudent(int studentId);
    boolean updateStudent(Student student);
    List<Student> selectAllStudent();
    //其他
    Student selectStudentById(int studentId);
    List<Student> selectStudentByName(String studentName);

}
