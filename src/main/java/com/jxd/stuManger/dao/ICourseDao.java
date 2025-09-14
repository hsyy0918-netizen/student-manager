package com.jxd.stuManger.dao;

import com.jxd.stuManger.model.Course;
import java.util.*;

public interface ICourseDao {
    //课程管理
    boolean insertCourse(Course course);

    boolean updateCourse(Course course);

    boolean delCourse(int courseId);
    List<Course> selectAllCourse();

    Course selectCourseById(int courseId);
    boolean isCourseSelected(int courseId);
    List<Course> selectTeacherCourses(int teacherId);
    List<Course> selectCoursesByName(String name);

}
