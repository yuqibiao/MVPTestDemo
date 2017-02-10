package com.yyyu.mvptestdemo.bean;

/**
 * 功能：User封装bean
 *
 * Created by yyyu on 2017/2/10.
 */

public class User {

    private String username;
    private String pwd;

    public User(){

    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
