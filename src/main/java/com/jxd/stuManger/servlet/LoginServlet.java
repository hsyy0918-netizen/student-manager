package com.jxd.stuManger.servlet;

import com.jxd.stuManger.model.*;
import com.jxd.stuManger.service.*;
import com.jxd.stuManger.service.impl.LoginServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private ILoginService loginService = new LoginServiceImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserLogin user = loginService.login(username, password);

        if (user != null) {
            req.getSession().setAttribute("currentUser", user);
            req.getSession().setAttribute("username", user.getUserName());

            // 根据角色重定向到不同主页
            if (user.getRole() == 1) { // 管理员
                resp.sendRedirect(req.getContextPath() + "/admin/admin_main.jsp");
            } else if (user.getRole() == 2) { // 教师
                resp.sendRedirect(req.getContextPath() + "/teacher/teacher_main.jsp");
            } else if (user.getRole() == 3) { // 学生
                resp.sendRedirect(req.getContextPath() + "/student/student_main.jsp");
            }
        } else {
            req.setAttribute("error", "用户名或密码错误！");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}