
package com.jxd.stuManger.service;

import com.jxd.stuManger.model.*;


import java.util.List;

public interface IAdminService {
    // 学生管理
    List<Student> getAllStudents();
    boolean addStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(int studentId);
    Student getStudentById(int studentId);
    List<Student> searchStudentsByName(String name); // 新增：按姓名搜索学生

    // 教师管理
    List<Teacher> getAllTeachers();
    boolean addTeacher(Teacher teacher);
    boolean updateTeacher(Teacher teacher);
    boolean deleteTeacher(int teacherId);
    Teacher getTeacherById(int teacherId);
    List<Teacher> searchTeachersByName(String name); // 新增：按姓名搜索教师

    // 课程管理
    List<Course> getAllCourses();
    boolean addCourse(Course course);
    boolean updateCourse(Course course);
    boolean deleteCourse(int courseId);
    Course getCourseById(int courseId);
    List<Course> searchCoursesByName(String name); // 新增：按课程名搜索课程

    // 通用
    List<College> getAllColleges();
    boolean resetUserPassword(String username);
}