package com.jxd.stuManger.service.impl;

import com.jxd.stuManger.dao.ICourseDao;
import com.jxd.stuManger.dao.ISelectedCourseDao;
import com.jxd.stuManger.model.Course;
import com.jxd.stuManger.model.SelectedCourse;
import com.jxd.stuManger.service.IStudentService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 何硕
 * @version 1.0
 * @className StudentService
 * @description TODO
 * @date 2025/9/10 17:38
 */
public class StudentService implements IStudentService {
    private ICourseDao courseDao;
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
    public List<Course> getAllAvailableCourses(int studentId) {
        SqlSession session = getSession();
        courseDao = session.getMapper(ICourseDao.class);
        selectedCourseDao = session.getMapper(ISelectedCourseDao.class);

        // 获取所有课程
        List<Course> allCourses = courseDao.selectAllCourse();
        // 获取该学生已选的课程
        List<SelectedCourse> selectedCourses = selectedCourseDao.selectStudentSelectedCourses(studentId);

        // 如果获取所有课程或已选课程列表为空，则直接返回空列表，避免空指针异常
        if (allCourses == null || allCourses.isEmpty()) {
            return new ArrayList<>();
        }

        List<Course> availableCourses = new ArrayList<>(allCourses);

        // 遍历已选课程，从所有课程列表中移除
        if (selectedCourses != null && !selectedCourses.isEmpty()) {
            for (SelectedCourse sc : selectedCourses) {
                // 使用流式操作进行过滤，更高效
                availableCourses.removeIf(course -> course.getCourseId() == sc.getCourseId());
            }
        }

        return availableCourses;
    }

    @Override
    public List<SelectedCourse> getSelectedCourses(int studentId) {
        SqlSession session = getSession();
        selectedCourseDao = session.getMapper(ISelectedCourseDao.class);
        return selectedCourseDao.selectStudentSelectedCourses(studentId);
    }

    @Override
    public List<SelectedCourse> getFinishedCourses(int studentId) {
        SqlSession session = getSession();
        selectedCourseDao = session.getMapper(ISelectedCourseDao.class);
        return selectedCourseDao.selectStudentFinishedCourses(studentId);
    }

    @Override
    public boolean selectCourse(int studentId, int courseId) {
        SqlSession session = getSession();
        selectedCourseDao = session.getMapper(ISelectedCourseDao.class);

        // 业务逻辑：选课前先检查是否已选过
        if (!selectedCourseDao.isCourseSelected(studentId, courseId)) {
            return selectedCourseDao.insertSelectedCourse(studentId, courseId);
        }
        return false;
    }

    @Override
    public boolean dropCourse(int studentId, int courseId) {
        SqlSession session = getSession();
        selectedCourseDao = session.getMapper(ISelectedCourseDao.class);

        // 业务逻辑：退课前检查是否已打分
        if (!selectedCourseDao.isMarked(studentId, courseId)) {
            return selectedCourseDao.deleteSelectedCourse(studentId, courseId);
        }
        return false;
    }
}
