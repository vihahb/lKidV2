package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/9/2018
 * Time: 12:22 AM
 * Email: vihahb@gmail.com
 */
public class UserLogin {
    @Expose
    private String user;
    @Expose
    private String pass;

    public UserLogin() {
    }

    public UserLogin(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
