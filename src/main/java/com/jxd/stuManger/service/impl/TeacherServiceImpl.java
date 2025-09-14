package com.jxd.stuManger.service.impl;

import com.jxd.stuManger.dao.*;
import com.jxd.stuManger.model.Course;
import com.jxd.stuManger.service.ITeacherService;
import com.jxd.stuManger.vo.StuWithSCourse;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author 何硕
 * @version 1.0
 * @className TeacherServiceImpl
 * @description TODO
 * @date 2025/9/14 19:37
 */
public class TeacherServiceImpl implements ITeacherService {
    private IStudentDao studentDao;
    private ICourseDao courseDao;
    private ITeacherDao teacherDao;
    private ICollegeDao collegeDao;
    private IUserLoginDao userLoginDao;
    private ISelectedCourseDao selectedCourseDao;


    public SqlSession getSession(){
        SqlSession session = null;
        try {
            InputStream in = Resources.getResourceAsStream("config.xml");
            SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
            session = sf.openSession(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return session;
    }

    @Override
    public List<Course> getTeacherCourses(int teacherId) {
        return courseDao.selectTeacherCourses(teacherId);
    }


    @Override
    public List<StuWithSCourse> getStudentsByCourse(int courseId) {
        return selectedCourseDao.selectStudentsByCourseId(courseId);
    }

    @Override
    public boolean gradeStudent(int studentId, int courseId, int mark) {
        // 业务逻辑：打分前检查是否已打分
        if (selectedCourseDao.isMarked(studentId, courseId)) {
            return false;
        }
        return selectedCourseDao.updateMark(studentId, courseId, mark);
    }
}

