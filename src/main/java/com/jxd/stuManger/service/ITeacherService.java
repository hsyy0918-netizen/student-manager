package com.jxd.stuManger.service;

import com.jxd.stuManger.model.Course;
import com.jxd.stuManger.model.SelectedCourse;

import java.util.List;

public interface ITeacherService {
    List<Course> getTeacherCourses(int teacherId);
    List<SelectedCourse> getStudentsByCourse(int courseId);
    boolean gradeStudent(int studentId, int courseId, int mark);
}
