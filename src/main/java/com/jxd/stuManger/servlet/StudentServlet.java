package com.jxd.stuManger.servlet;

import com.jxd.stuManger.dao.*;
import com.jxd.stuManger.model.*;
import com.jxd.stuManger.service.*;
import com.jxd.stuManger.service.impl.*;
import com.jxd.stuManger.vo.CourseWithSCourse;
import com.jxd.stuManger.vo.StuWithSCourse;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/studentServlet")
public class StudentServlet extends HttpServlet {
    private IStudentService studentService = new StudentServiceImpl();
    private ILoginService loginService = new LoginServiceImpl();


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        UserLogin user = (UserLogin) req.getSession().getAttribute("currentUser");

        if (user == null || user.getRole() != 3) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        int studentId = Integer.parseInt(user.getUserName());

        switch (action) {
            case "allCourses":
                allCourses(req, resp, studentId);
                break;
            case "selectedCourses":
                selectedCourses(req, resp, studentId);
                break;
            case "finishedCourses":
                finishedCourses(req, resp, studentId);
                break;
            case "selectCourse":
                selectCourse(req, resp, studentId);
                break;
            case "dropCourse":
                dropCourse(req, resp, studentId);
                break;
            case "changePassword":
                changePassword(req, resp, user.getUserName());
                break;
            default:
                allCourses(req, resp, studentId);
                break;
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    // StudentServlet.java 的相关部分
    private void allCourses(HttpServletRequest req, HttpServletResponse resp, int studentId) throws ServletException, IOException {
        // 获取所有课程
        List<Course> allCourses = studentService.getAllAvailableCourses(studentId);

        // 获取已选但未打分的课程列表
        List<CourseWithSCourse> selectedCourses = studentService.getSelectedCourses(studentId);

        // 获取已修完并已打分的课程列表
        List<CourseWithSCourse> finishedCourses = studentService.getFinishedCourses(studentId);

        // 创建一个包含所有已选和已修课程的合并列表
        List<CourseWithSCourse> takenCourses = new ArrayList<>();
        takenCourses.addAll(selectedCourses);
        takenCourses.addAll(finishedCourses);

        // 将所有课程和合并后的已选课程列表都放入请求作用域
        req.setAttribute("allCourses", allCourses);
        req.setAttribute("takenCourses", takenCourses);

        req.getRequestDispatcher("/student/all_courses.jsp").forward(req, resp);
    }

    private void selectedCourses(HttpServletRequest req, HttpServletResponse resp, int studentId) throws ServletException, IOException {
        List<CourseWithSCourse> courses = studentService.getSelectedCourses(studentId);
        req.setAttribute("courses", courses);
        req.getRequestDispatcher("/student/selected_courses.jsp").forward(req, resp);
    }

    private void finishedCourses(HttpServletRequest req, HttpServletResponse resp, int studentId) throws ServletException, IOException {
        List<CourseWithSCourse> courses = studentService.getFinishedCourses(studentId);
        req.setAttribute("courses", courses);
        req.getRequestDispatcher("/student/finished_courses.jsp").forward(req, resp);
    }

    private void selectCourse(HttpServletRequest req, HttpServletResponse resp, int studentId) throws IOException {
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        if (studentService.selectCourse(studentId, courseId)) {
            resp.getWriter().println("success");
        } else {
            resp.getWriter().println("failed");
        }
    }

    private void dropCourse(HttpServletRequest req, HttpServletResponse resp, int studentId) throws IOException {
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        if (studentService.dropCourse(studentId, courseId)) {
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