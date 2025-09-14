package com.jxd.stuManger.service;

import com.jxd.stuManger.model.UserLogin;

/**
 * @author 何硕
 * @version 1.0
 * @className ILoginService
 * @description TODO
 * @date 2025/9/10 23:39
 */
public interface ILoginService {
    UserLogin login(String userName, String password);
    boolean changePassword(String username, String oldPassword, String newPassword);

}
