package com.jxd.stuManger.service;

import com.jxd.stuManger.model.Course;
import com.jxd.stuManger.model.SelectedCourse;
import com.jxd.stuManger.model.Student;
import java.util.*;

public interface IStudentService {
    //展示数据
    List<Course> getAllAvailableCourses(int studentId);
    List<SelectedCourse> getSelectedCourses(int studentId);
    List<SelectedCourse> getFinishedCourses(int studentId);
    boolean selectCourse(int studentId, int courseId);
    boolean dropCourse(int studentId, int courseId);
}
