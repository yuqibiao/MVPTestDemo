package com.yyyu.mvptestdemo.presenter;

import com.yyyu.mvptestdemo.bean.User;
import com.yyyu.mvptestdemo.model.ILoginBiz;
import com.yyyu.mvptestdemo.model.LoginBiz;
import com.yyyu.mvptestdemo.model.OnLoginListener;
import com.yyyu.mvptestdemo.view.ILoginView;

/**
 * 功能：登录操作Presenter
 *
 * Created by yyyu on 2017/2/10.
 */

public class LoginPresenter {

    private ILoginView mLoginView;
    private  ILoginBiz mLoginBiz;

    public LoginPresenter(ILoginView loginView){
        this.mLoginView = loginView;
        this.mLoginBiz = new LoginBiz();
    }

    public void login(){
        mLoginView.showLoadingDialog();
        mLoginBiz.login(mLoginView.getUsername(), mLoginView.getPwd(), new OnLoginListener() {
            @Override
            public void onSuccess(User user) {
                mLoginView.closeLoadingDialog();
                mLoginView.toMainActivity(user);
            }
            @Override
            public void onFailed(String tip) {
                mLoginView.closeLoadingDialog();
                mLoginView.showFailedToast(tip);
            }
        });
    }

    public void clear(){
        mLoginBiz.clear(mLoginView.getUsernameEt() , mLoginView.getPwdEt());
    }

}
