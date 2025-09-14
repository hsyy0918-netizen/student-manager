package com.jxd.stuManger.dao;

import com.jxd.stuManger.model.UserLogin;

/**
 * @author 何硕
 * @version 1.0
 * @className IUserLoginDao
 * @description TODO
 * @date 2025/9/10 23:13
 */
public interface IUserLoginDao {
    //登录
    UserLogin selectByUserName(String userName);
    boolean insertUserLogin(UserLogin userLogin);
    boolean deleteUserLoginByUsername(String username);
    boolean updatePassword(String username, String newPassword);
    UserLogin selectUserByUsername(String username);
    boolean deleteUserLogin(String username);
}
