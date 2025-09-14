package com.jxd.stuManger.dao;

import com.jxd.stuManger.model.SelectedCourse;

import java.util.List;

public interface ISelectedCourseDao {
    //成绩表 courseId studentId mark
    int insertSelectedCourse(int studentId, int courseId);
    boolean deleteSelectedCourse(int studentId, int courseId);

    List<SelectedCourse> selectStudentSelectedCourses(int studentId);
    List<SelectedCourse> selectStudentFinishedCourses(int studentId);
    List<SelectedCourse> selectStudentsByCourseId(int courseId);
    boolean updateMark(int studentId, int courseId, int mark);
    boolean isCourseSelected(int studentId, int courseId);
    boolean isMarked(int studentId, int courseId);
}
