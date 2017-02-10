package com.yyyu.mvptestdemo.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yyyu.mvptestdemo.R;
import com.yyyu.mvptestdemo.bean.User;
import com.yyyu.mvptestdemo.presenter.LoginPresenter;

public class loginActivity extends AppCompatActivity implements  ILoginView{

    private EditText etUsername;
    private EditText etPwd;
    private Button btnLogin;
    private Button btnClear;
    private LoginPresenter mLoginPresenter;
    private ProgressDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        mLoginPresenter = new LoginPresenter(this);
        initView();
        initListener();
    }

    private void initView() {
        etUsername = (EditText) findViewById(R.id.et_username);
        etPwd = (EditText) findViewById(R.id.et_pwd);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnClear = (Button) findViewById(R.id.btn_clear);

        loadingDialog = new ProgressDialog(this);
        loadingDialog.setTitle("登录中....");
    }

    private void initListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.login();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.clear();
            }
        });
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
        MainActivity.startAction(this , user.getUsername());
    }

    @Override
    public void showFailedToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
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
