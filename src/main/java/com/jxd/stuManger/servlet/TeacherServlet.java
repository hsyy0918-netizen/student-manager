package com.jxd.stuManger.servlet;

import com.jxd.stuManger.model.*;
import com.jxd.stuManger.service.*;
import com.jxd.stuManger.service.impl.*;
import com.jxd.stuManger.vo.StuWithSCourse;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/teacherServlet")
public class TeacherServlet extends HttpServlet {
    private ITeacherService teacherService = new TeacherServiceImpl();
    private ILoginService loginService = new LoginServiceImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        UserLogin user = (UserLogin) req.getSession().getAttribute("currentUser");

        if (user == null || user.getRole() != 2) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        int teacherId = Integer.parseInt(user.getUserName());

        switch (action) {
            case "myCourses":
                myCourses(req, resp, teacherId);
                break;
            case "studentsByCourse":
                studentsByCourse(req, resp);
                break;
            case "gradeStudent":
                gradeStudent(req, resp);
                break;
            case "changePassword":
                changePassword(req, resp, user.getUserName());
                break;
            default:
                myCourses(req, resp, teacherId);
                break;
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    private void myCourses(HttpServletRequest req, HttpServletResponse resp, int teacherId) throws ServletException, IOException {
        List<Course> courses = teacherService.getTeacherCourses(teacherId);
        req.setAttribute("courses", courses);
        req.getRequestDispatcher("/teacher/my_courses.jsp").forward(req, resp);
    }

    private void studentsByCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        String courseName = req.getParameter("courseName");
        List<StuWithSCourse> students = teacherService.getStudentsByCourse(courseId);
        req.setAttribute("students", students);
        req.setAttribute("courseId", courseId);
        req.setAttribute("courseName", courseName);
        req.getRequestDispatcher("/teacher/student_grade_list.jsp").forward(req, resp);
    }

    private void gradeStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        int mark = Integer.parseInt(req.getParameter("mark"));

        if (teacherService.gradeStudent(studentId, courseId, mark)) {
            resp.getWriter().println("success");
        } else {
            resp.getWriter().println("failed");
        }
    }

    private void changePassword(HttpServletRequest req, HttpServletResponse resp, String username) throws ServletException, IOException {
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");

        if (loginService.changePassword(username, oldPassword, newPassword)) {
            req.getSession().invalidate();
            resp.getWriter().println("success");
        } else {
            resp.getWriter().println("failed");
        }
    }
}