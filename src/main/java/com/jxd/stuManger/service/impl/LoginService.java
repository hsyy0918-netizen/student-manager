package com.jxd.stuManger.service.impl;

import com.jxd.stuManger.dao.IUserLoginDao;
import com.jxd.stuManger.model.Student;
import com.jxd.stuManger.model.UserLogin;
import com.jxd.stuManger.service.ILoginService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author 何硕
 * @version 1.0
 * @className LoginService
 * @description TODO
 * @date 2025/9/11 8:44
 */
public class LoginService implements ILoginService {
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


    @Override
    public UserLogin login(String userName, String password) {
        SqlSession session = getSession();
        userLoginDao = session.getMapper(IUserLoginDao.class);
        UserLogin user = userLoginDao.selectUserByUsername(userName);
        if (user != null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        SqlSession session = getSession();
        userLoginDao = session.getMapper(IUserLoginDao.class);

        UserLogin user = userLoginDao.selectUserByUsername(username);
        if (user != null && user.getPassword().equals(oldPassword)) {
            return userLoginDao.updatePassword(username, newPassword);
        }
        return false;
    }
}
