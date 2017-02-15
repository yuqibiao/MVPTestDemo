package com.yyyu.mvptestdemo.model.inter;

import android.widget.EditText;

import com.yyyu.mvptestdemo.callback.OnLoginListener;

/**
 * 功能：登录操作业务逻辑接口
 *
 * Created by yyyu on 2017/2/10.
 */

public interface ILoginBiz {

    void login(String username , String pwd , OnLoginListener onLoginListener);
    void clear(EditText etUsername , EditText etPwd);

}
