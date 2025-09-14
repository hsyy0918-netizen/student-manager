package com.jxd.stuManger.service;

import com.jxd.stuManger.model.Course;
import com.jxd.stuManger.model.SelectedCourse;
import com.jxd.stuManger.vo.StuWithSCourse;

import java.util.List;

public interface ITeacherService {
    List<Course> getTeacherCourses(int teacherId);
    List<StuWithSCourse> getStudentsByCourse(int courseId);
    boolean gradeStudent(int studentId, int courseId, int mark);//sc
}
