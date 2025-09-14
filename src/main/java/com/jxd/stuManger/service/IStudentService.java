package com.jxd.stuManger.service;

import com.jxd.stuManger.model.Course;
import com.jxd.stuManger.model.SelectedCourse;
import com.jxd.stuManger.model.Student;
import com.jxd.stuManger.vo.CourseWithSCourse;

import java.util.*;

public interface IStudentService {
    //展示数据
    List<Course> getAllAvailableCourses(int studentId);
    List<CourseWithSCourse> getSelectedCourses(int studentId);//已选未休完
    List<CourseWithSCourse> getFinishedCourses(int studentId);//修完已修完
    boolean selectCourse(int studentId, int courseId);
    boolean dropCourse(int studentId, int courseId);
}
