package com.jxd.stuManger.service.impl;

import com.jxd.stuManger.dao.ICollegeDao;
import com.jxd.stuManger.dao.ICourseDao;
import com.jxd.stuManger.dao.IStudentDao;
import com.jxd.stuManger.dao.ITeacherDao;
import com.jxd.stuManger.dao.IUserLoginDao;
import com.jxd.stuManger.model.College;
import com.jxd.stuManger.model.Course;
import com.jxd.stuManger.model.Student;
import com.jxd.stuManger.model.Teacher;
import com.jxd.stuManger.model.UserLogin;
import com.jxd.stuManger.service.IAdminService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AdminServiceImpl implements IAdminService {
    // 依赖于 DAO 层接口，实现业务逻辑
    private IStudentDao studentDao;
    private ITeacherDao teacherDao;
    private ICourseDao courseDao;
    private ICollegeDao collegeDao;
    private IUserLoginDao userLoginDao;

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

    // 学生管理方法
    @Override
    public List<Student> getAllStudents() {
        SqlSession session = getSession();
        studentDao = session.getMapper(IStudentDao.class);
        return studentDao.selectAllStudent();
    }

    @Override
    public boolean addStudent(Student student) {
        SqlSession session = getSession();
        studentDao = session.getMapper(IStudentDao.class);
        userLoginDao = session.getMapper(IUserLoginDao.class);
        // 业务逻辑：新增学生成功后，需要为其创建一个默认的登录账户
        int studentId = studentDao.insertStudent(student);
        if (studentId > 0) {
            // 学生记录插入成功
            UserLogin user = new UserLogin();
            user.setUserName(String.valueOf(studentId)); // 用户名就是学号
            user.setPassword("123"); // 默认密码
            user.setRole(3); // 角色为学生
            return userLoginDao.insertUserLogin(user);
        }
        return false;
    }

    @Override
    public boolean updateStudent(Student student) {
        SqlSession session = getSession();
        studentDao = session.getMapper(IStudentDao.class);
        return studentDao.updateStudent(student);
    }

    @Override
    public boolean deleteStudent(int studentId) {
        SqlSession session = getSession();
        studentDao = session.getMapper(IStudentDao.class);
        userLoginDao = session.getMapper(IUserLoginDao.class);
        // 业务逻辑：删除学生记录的同时，也删除其登录账户
        if (studentDao.delStudent(studentId)) {
            return userLoginDao.deleteUserLogin(String.valueOf(studentId));
        }
        return false;
    }

    @Override
    public Student getStudentById(int studentId) {
        SqlSession session = getSession();
        studentDao = session.getMapper(IStudentDao.class);
        return studentDao.selectStudentById(studentId);
    }

    @Override
    public List<Student> searchStudentsByName(String name) {
        SqlSession session = getSession();
        studentDao = session.getMapper(IStudentDao.class);
        return studentDao.selectStudentByName(name);
    }

    // 教师管理方法
    @Override
    public List<Teacher> getAllTeachers() {
        SqlSession session = getSession();
        teacherDao = session.getMapper(ITeacherDao.class);
        return teacherDao.selectAllTeacher();
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
        SqlSession session = getSession();
        teacherDao = session.getMapper(ITeacherDao.class);
        userLoginDao = session.getMapper(IUserLoginDao.class);
        // 业务逻辑：新增教师成功后，需要为其创建一个默认的登录账户
        if (teacherDao.insertTeacher(teacher) > 0) {
            // 教师记录插入成功
            UserLogin user = new UserLogin();
            user.setUserName(String.valueOf(teacher.getTeacherId())); // 用户名就是教工号
            user.setPassword("123"); // 默认密码
            user.setRole(2); // 角色为教师
            return userLoginDao.insertUserLogin(user);
        }
        return false;
    }

    @Override
    public boolean updateTeacher(Teacher teacher) {
        SqlSession session = getSession();
        teacherDao = session.getMapper(ITeacherDao.class);
        return teacherDao.updateTeacher(teacher);
    }

    @Override
    public boolean deleteTeacher(int teacherId) {
        SqlSession session = getSession();
        teacherDao = session.getMapper(ITeacherDao.class);
        userLoginDao = session.getMapper(IUserLoginDao.class);
        // 业务逻辑：删除教师记录的同时，也删除其登录账户
        if (teacherDao.delTeacher(teacherId)) {
            return userLoginDao.deleteUserLogin(String.valueOf(teacherId));
        }
        return false;
    }

    @Override
    public Teacher getTeacherById(int teacherId) {
        SqlSession session = getSession();
        teacherDao = session.getMapper(ITeacherDao.class);
        return teacherDao.selectTeacherById(teacherId);
    }

    @Override
    public List<Teacher> searchTeachersByName(String name) {
        SqlSession session = getSession();
        teacherDao = session.getMapper(ITeacherDao.class);
        return teacherDao.selectTacherByName(name);
    }

    // 课程管理方法
    @Override
    public List<Course> getAllCourses() {
        SqlSession session = getSession();
        courseDao = session.getMapper(ICourseDao.class);
        return courseDao.selectAllCourse();
    }

    @Override
    public boolean addCourse(Course course) {
        SqlSession session = getSession();
        courseDao = session.getMapper(ICourseDao.class);
        return courseDao.insertCourse(course);
    }

    @Override
    public boolean updateCourse(Course course) {
        SqlSession session = getSession();
        courseDao = session.getMapper(ICourseDao.class);
        return courseDao.updateCourse(course);
    }

    @Override
    public boolean deleteCourse(int courseId) {
        SqlSession session = getSession();
        courseDao = session.getMapper(ICourseDao.class);
        return courseDao.delCourse(courseId);
    }

    @Override
    public Course getCourseById(int courseId) {
        SqlSession session = getSession();
        courseDao = session.getMapper(ICourseDao.class);
        return courseDao.selectCourseById(courseId);
    }

    @Override
    public List<Course> searchCoursesByName(String name) {
        SqlSession session = getSession();
        courseDao = session.getMapper(ICourseDao.class);
        return courseDao.selectCoursesByName(name);
    }

    // 通用方法
    @Override
    public List<College> getAllColleges() {
        SqlSession session = getSession();
        collegeDao = session.getMapper(ICollegeDao.class);
        return collegeDao.selectAllCollege();
    }

    @Override
    public boolean resetUserPassword(String username) {
        SqlSession session = getSession();
        userLoginDao = session.getMapper(IUserLoginDao.class);
        return userLoginDao.updatePassword(username, "123");
    }
}