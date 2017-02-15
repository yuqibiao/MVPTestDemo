package com.yyyu.mvptestdemo.view.inter;

import android.widget.EditText;

import com.yyyu.mvptestdemo.bean.User;

/**
 * 功能：登录接口
 *
 * Created by yyyu on 2017/2/10.
 */

public interface ILoginView {

    String getUsername();
    String getPwd();
    void showLoadingDialog();
    void closeLoadingDialog();
    void toMainActivity(User user);
    void showFailedToast(String str);
    EditText getUsernameEt();
    EditText getPwdEt();

}
