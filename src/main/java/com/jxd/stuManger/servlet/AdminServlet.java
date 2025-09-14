package com.jxd.stuManger.servlet;

import com.jxd.stuManger.model.*;
import com.jxd.stuManger.service.*;
import com.jxd.stuManger.service.impl.AdminServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/adminServlet")
public class AdminServlet extends HttpServlet {
    private IAdminService adminService = new AdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if (action == null || action.isEmpty()) {
            listStudents(req, resp);
            return;
        }

        switch (action) {
            case "listStudents":
                listStudents(req, resp);
                break;
            case "addStudent":
                addStudent(req, resp);
                break;
            case "deleteStudent":
                deleteStudent(req, resp);
                break;
            case "toEditStudent":
                toEditStudent(req, resp);
                break;
            case "editStudent":
                editStudent(req, resp);
                break;
            case "toAddStudent":
                toAddStudent(req, resp);
                break;
            case "listTeachers":
                listTeachers(req, resp);
                break;
            case "addTeacher":
                addTeacher(req, resp);
                break;
            case "deleteTeacher":
                deleteTeacher(req, resp);
                break;
            case "toEditTeacher":
                toEditTeacher(req, resp);
                break;
            case "editTeacher":
                editTeacher(req, resp);
                break;
            case "toAddTeacher":
                toAddTeacher(req, resp);
                break;
            case "listCourses":
                listCourses(req, resp);
                break;
            case "addCourse":
                addCourse(req, resp);
                break;
            case "deleteCourse":
                deleteCourse(req, resp);
                break;
            case "toEditCourse":
                toEditCourse(req, resp);
                break;
            case "editCourse":
                editCourse(req, resp);
                break;
            case "toAddCourse":
                toAddCourse(req, resp);
                break;
            case "resetPassword":
                resetPassword(req, resp);
                break;
            default:
                listStudents(req, resp);
                break;
        }
    }

    // 学生管理方法
    private void listStudents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        List<Student> students;
        if (name != null && !name.isEmpty()) {
            students = adminService.searchStudentsByName(name);
        } else {
            students = adminService.getAllStudents();
        }
        List<College> colleges = adminService.getAllColleges();
        req.setAttribute("students", students);
        req.setAttribute("colleges", colleges);
        req.getRequestDispatcher("/admin/student_list.jsp").forward(req, resp);
    }

    private void toAddStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("colleges", adminService.getAllColleges());
        req.getRequestDispatcher("/admin/student_add.jsp").forward(req, resp);
    }

    private void addStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("studentName");
        String sex = req.getParameter("sex");
        String birthdayStr = req.getParameter("birthday");
        String enrollmentDateStr = req.getParameter("enrollmentDate");
        int collegeId = Integer.parseInt(req.getParameter("collegeId"));

        Student student = new Student();
        student.setStudentName(name);
        student.setSex(sex);
        student.setCollegeId(collegeId);
        student.setGrade(enrollmentDateStr);

        if (adminService.addStudent(student)) {
            resp.sendRedirect(req.getContextPath() + "/adminServlet?action=listStudents");
        } else {
            req.setAttribute("errorMsg", "添加学生失败！");
            toAddStudent(req, resp); // 添加失败，回到添加页面
        }
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        if (adminService.deleteStudent(studentId)) {
            resp.getWriter().println("success");
        } else {
            resp.getWriter().println("failed");
        }
    }

    private void toEditStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        Student student = adminService.getStudentById(studentId);
        List<College> colleges = adminService.getAllColleges();
        req.setAttribute("student", student);
        req.setAttribute("colleges", colleges);
        req.getRequestDispatcher("/admin/student_edit.jsp").forward(req, resp);
    }

    private void editStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        String name = req.getParameter("studentName");
        String sex = req.getParameter("sex");
        String birthdayStr = req.getParameter("birthday");
        String enrollmentDateStr = req.getParameter("enrollmentDate");
        int collegeId = Integer.parseInt(req.getParameter("collegeId"));

        Student student = new Student();
        student.setStudentId(studentId);
        student.setStudentName(name);
        student.setSex(sex);
        student.setCollegeId(collegeId);

        student.setGrade(enrollmentDateStr);

        if (adminService.updateStudent(student)) {
            resp.sendRedirect(req.getContextPath() + "/adminServlet?action=listStudents");
        } else {
            req.setAttribute("errorMsg", "修改学生失败！");
            toEditStudent(req, resp);
        }
    }

    // 教师管理方法
    private void listTeachers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        List<Teacher> teachers;
        if (name != null && !name.isEmpty()) {
            teachers = adminService.searchTeachersByName(name);
        } else {
            teachers = adminService.getAllTeachers();
        }
        List<College> colleges = adminService.getAllColleges();
        req.setAttribute("teachers", teachers);
        req.setAttribute("colleges", colleges);
        req.getRequestDispatcher("/admin/teacher_list.jsp").forward(req, resp);
    }

    private void toAddTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("colleges", adminService.getAllColleges());
        req.getRequestDispatcher("/admin/teacher_add.jsp").forward(req, resp);
    }

    private void addTeacher(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("teacherName");
        String sex = req.getParameter("sex");
        String birthdayStr = req.getParameter("birthday");
        String degree = req.getParameter("degree");
        String title = req.getParameter("title");
        String hireDateStr = req.getParameter("hireDate");
        int collegeId = Integer.parseInt(req.getParameter("collegeId"));

        Teacher teacher = new Teacher();
        teacher.setTeacherName(name);
        teacher.setSex(sex);
        teacher.setDegree(degree);
        teacher.setTitle(title);
        teacher.setCollegeId(collegeId);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        teacher.setBirthday(birthdayStr);
        teacher.setHireDate(hireDateStr);

        if (adminService.addTeacher(teacher)) {
            resp.sendRedirect(req.getContextPath() + "/adminServlet?action=listTeachers");
        } else {
            req.setAttribute("errorMsg", "添加教师失败！");
            toAddTeacher(req, resp); // 添加失败，回到添加页面
        }
    }

    private void deleteTeacher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int teacherId = Integer.parseInt(req.getParameter("teacherId"));
        if (adminService.deleteTeacher(teacherId)) {
            resp.getWriter().println("success");
        } else {
            resp.getWriter().println("failed");
        }
    }

    private void toEditTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int teacherId = Integer.parseInt(req.getParameter("teacherId"));
        Teacher teacher = adminService.getTeacherById(teacherId);
        List<College> colleges = adminService.getAllColleges();
        req.setAttribute("teacher", teacher);
        req.setAttribute("colleges", colleges);
        req.getRequestDispatcher("/admin/teacher_edit.jsp").forward(req, resp);
    }

    private void editTeacher(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int teacherId = Integer.parseInt(req.getParameter("teacherId"));
        String name = req.getParameter("teacherName");
        String sex = req.getParameter("sex");
        String birthdayStr = req.getParameter("birthday");
        String degree = req.getParameter("degree");
        String title = req.getParameter("title");
        String hireDateStr = req.getParameter("hireDate");
        int collegeId = Integer.parseInt(req.getParameter("collegeId"));

        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherId);
        teacher.setTeacherName(name);
        teacher.setSex(sex);
        teacher.setDegree(degree);
        teacher.setTitle(title);
        teacher.setCollegeId(collegeId);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        teacher.setBirthday(birthdayStr);
        teacher.setHireDate(hireDateStr);

        if (adminService.updateTeacher(teacher)) {
            resp.sendRedirect(req.getContextPath() + "/adminServlet?action=listTeachers");
        } else {
            req.setAttribute("errorMsg", "修改教师失败！");
            toEditTeacher(req, resp);
        }
    }

    // 课程管理方法
    private void listCourses(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        List<Course> courses;
        if (name != null && !name.isEmpty()) {
            courses = adminService.searchCoursesByName(name);
        } else {
            courses = adminService.getAllCourses();
        }
        List<Teacher> teachers = adminService.getAllTeachers();
        List<College> colleges = adminService.getAllColleges();
        req.setAttribute("courses", courses);
        req.setAttribute("teachers", teachers);
        req.setAttribute("colleges", colleges);
        req.getRequestDispatcher("/admin/course_list.jsp").forward(req, resp);
    }

    private void toAddCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("teachers", adminService.getAllTeachers());
        req.setAttribute("colleges", adminService.getAllColleges());
        req.getRequestDispatcher("/admin/course_add.jsp").forward(req, resp);
    }

    private void addCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String courseName = req.getParameter("courseName");
        int teacherId = Integer.parseInt(req.getParameter("teacherId"));
        String courseTime = req.getParameter("courseTime");
        String classroom = req.getParameter("classroom");
        int classWeek = Integer.parseInt(req.getParameter("classWeek"));
        String courseType = req.getParameter("courseType");
        int collegeId = Integer.parseInt(req.getParameter("collegeId"));
        int score = Integer.parseInt(req.getParameter("score"));

        Course course = new Course();
        course.setCourseName(courseName);
        course.setTeacherId(teacherId);
        course.setCourseTime(courseTime);
        course.setClassroom(classroom);
        course.setClassWeek(classWeek);
        course.setType(courseType);
        course.setCollegeId(collegeId);
        course.setScore(score);

        if (adminService.addCourse(course)) {
            resp.sendRedirect(req.getContextPath() + "/adminServlet?action=listCourses");
        } else {
            req.setAttribute("errorMsg", "添加课程失败！");
            toAddCourse(req, resp); // 添加失败，回到添加页面
        }
    }

    private void deleteCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        if (adminService.deleteCourse(courseId)) {
            resp.getWriter().println("success");
        } else {
            resp.getWriter().println("failed");
        }
    }

    private void toEditCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        Course course = adminService.getCourseById(courseId);
        List<Teacher> teachers = adminService.getAllTeachers();
        List<College> colleges = adminService.getAllColleges();
        req.setAttribute("course", course);
        req.setAttribute("teachers", teachers);
        req.setAttribute("colleges", colleges);
        req.getRequestDispatcher("/admin/course_edit.jsp").forward(req, resp);
    }

    private void editCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        String courseName = req.getParameter("courseName");
        int teacherId = Integer.parseInt(req.getParameter("teacherId"));
        String courseTime = req.getParameter("courseTime");
        String classroom = req.getParameter("classroom");
        int classWeek = Integer.parseInt(req.getParameter("classWeek"));
        String courseType = req.getParameter("courseType");
        int collegeId = Integer.parseInt(req.getParameter("collegeId"));
        int score = Integer.parseInt(req.getParameter("score"));

        Course course = new Course();
        course.setCourseId(courseId);
        course.setCourseName(courseName);
        course.setTeacherId(teacherId);
        course.setCourseTime(courseTime);
        course.setClassroom(classroom);
        course.setClassWeek(classWeek);
        course.setType(courseType);
        course.setCollegeId(collegeId);
        course.setScore(score);

        if (adminService.updateCourse(course)) {
            resp.sendRedirect(req.getContextPath() + "/adminServlet?action=listCourses");
        } else {
            req.setAttribute("errorMsg", "修改课程失败！");
            toEditCourse(req, resp);
        }
    }

    private void resetPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        if (adminService.resetUserPassword(username)) {
            resp.getWriter().println("success");
        } else {
            resp.getWriter().println("failed");
        }
    }
}