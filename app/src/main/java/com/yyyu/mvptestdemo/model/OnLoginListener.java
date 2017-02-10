package com.yyyu.mvptestdemo.model;

import com.yyyu.mvptestdemo.bean.User;

/**
 * 功能：登录操作结果回调接口
 *
 * Created by yyyu on 2017/2/10.
 */

public interface OnLoginListener {

    void onSuccess(User user);

    void onFailed(String tip);

}
