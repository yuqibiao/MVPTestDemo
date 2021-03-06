package com.yyyu.mvptestdemo.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yyyu.mvptestdemo.R;
import com.yyyu.mvptestdemo.bean.User;
import com.yyyu.mvptestdemo.di.component.DaggerLoginActivityComponent;
import com.yyyu.mvptestdemo.di.module.LoginActivityModule;
import com.yyyu.mvptestdemo.presenter.LoginPresenter;
import com.yyyu.mvptestdemo.view.inter.ILoginView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_clear)
    Button btnClear;

    @Inject
    LoginPresenter mLoginPresenter;
    @Inject
    ProgressDialog loadingDialog;
    @Inject
    Context mContext;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        DaggerLoginActivityComponent.builder()
                .baseActivityComponent(getmBaseActCom())
                .loginActivityModule(new LoginActivityModule(this))
                .build()
                .inject(this);
        loadingDialog.setTitle("登录中....");
    }

    @OnClick(R.id.btn_login)
    public void toLogin(View v){
        mLoginPresenter.login();
        Log.e(TAG, "toLogin: ====Context=="+mContext );
    }

    @OnClick(R.id.btn_clear)
    public void toClear(View v){
        mLoginPresenter.clear();
    }


    @Override
    public String getUsername() {
        return etUsername.getText().toString();
    }

    @Override
    public String getPwd() {
        return etPwd.getText().toString();
    }

    @Override
    public void showLoadingDialog() {
        loadingDialog.show();
    }

    @Override
    public void closeLoadingDialog() {
        loadingDialog.hide();
    }

    @Override
    public void toMainActivity(User user) {
        MainActivity.startAction(this, user.getUsername());
    }

    @Override
    public void showFailedToast(String str) {
        toastUtils.showToast("登录失败---来着IOC");
        //Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public EditText getUsernameEt() {
        return etUsername;
    }

    @Override
    public EditText getPwdEt() {
        return etPwd;
    }
}
