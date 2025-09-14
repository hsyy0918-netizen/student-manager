package com.jxd.stuManger.servlet;

import com.jxd.stuManger.model.UserLogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 何硕
 * @version 1.0
 * @className LoginServlet
 * @description TODO
 * @date 2025/9/10 23:35
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");

        int role =0;//之后获取
        HttpSession session = req.getSession();
        session.setAttribute("role",role);

        //转发到index.jsp ,role在jsp里校验
        //role = 0,返回error



        switch (role){
            case 1:
                req.getRequestDispatcher("/admin").forward(req,resp);
                break;
            case 2:
                req.getRequestDispatcher("/teacher").forward(req,resp);
                break;
            case 3:
                req.getRequestDispatcher("/student").forward(req,resp);
                break;
            default:
                session.setAttribute("error","用户名或者密码错误");
                req.getRequestDispatcher("/login").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
