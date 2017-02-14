package com.yyyu.mvptestdemo.bean;

import javax.inject.Inject;

/**
 * 功能：测试Dagger用的bean
 *
 * Created by yyyu on 2017/2/13.
 */

public class DaggerTestBean {

    private String str;

    @Inject
    public DaggerTestBean() {
        str = "Dagger使用测试";
    }

    public DaggerTestBean(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
