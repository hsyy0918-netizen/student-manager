package com.jxd.stuManger.model;
/**
 * 登录账户实体类（对应表：userLogin）
 */
//s和t继承他会好一点吗
public class UserLogin{
   private int userId;
   private String userName;
   private String password;
   private int role;

   public int getUserId() {
      return userId;
   }

   public void setUserId(int userId) {
      this.userId = userId;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public int getRole() {
      return role;
   }

   public void setRole(int role) {
      this.role = role;
   }

   public UserLogin(int userId, String userName, String password, int role) {
      this.userId = userId;
      this.userName = userName;
      this.password = password;
      this.role = role;
   }
   public UserLogin() {

   }
}