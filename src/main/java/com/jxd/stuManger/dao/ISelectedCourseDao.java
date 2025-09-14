package com.jxd.stuManger.dao;

import com.jxd.stuManger.model.SelectedCourse;
import com.jxd.stuManger.vo.CourseWithSCourse;
import com.jxd.stuManger.vo.StuWithSCourse;

import java.util.List;

public interface ISelectedCourseDao {
    //成绩表 courseId studentId mark
    boolean insertSelectedCourse(int studentId, int courseId);
    boolean deleteSelectedCourse(int studentId, int courseId);

    List<CourseWithSCourse> selectStudentSelectedCourses(int studentId);
    List<CourseWithSCourse> selectStudentFinishedCourses(int studentId);
    List<StuWithSCourse> selectStudentsByCourseId(int courseId);
    boolean updateMark(int studentId, int courseId, int mark);
    boolean isCourseSelected(int studentId, int courseId);
    boolean isMarked(int studentId, int courseId);
}
