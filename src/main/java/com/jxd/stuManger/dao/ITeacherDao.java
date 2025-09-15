package com.jxd.stuManger.dao;

import com.jxd.stuManger.model.Teacher;
import java.util.*;

public interface ITeacherDao {
    //教师管理
    int insertTeacher(Teacher teacher);
    boolean updateTeacher(Teacher teacher);
    boolean delTeacher(int teacherId);
    List<Teacher> selectAllTeacher();
    List<Teacher> selectTeacherByName(String teacherName);//?这是那个功能啊

    //其他
    Teacher selectTeacherById(int teacherId);

}
