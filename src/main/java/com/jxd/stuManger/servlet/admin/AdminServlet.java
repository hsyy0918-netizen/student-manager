package com.jxd.stuManger.servlet.admin;

import com.jxd.stuManger.service.IAdminService;
import com.jxd.stuManger.service.impl.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTML;
import java.io.IOException;

/**
 * @author 何硕
 * @version 1.0
 * @className AdminServlet
 * @description TODO
 * @date 2025/9/11 8:33
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private IAdminService adminService = new AdminService();
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
}
