package com.yyyu.mvptestdemo.model;

import android.os.Handler;
import android.os.Message;
import android.widget.EditText;

import com.yyyu.mvptestdemo.bean.User;
import com.yyyu.mvptestdemo.model.inter.ILoginBiz;
import com.yyyu.mvptestdemo.callback.OnLoginListener;

/**
 * 功能：登录操作业务逻辑接口实现类
 *
 * Created by yyyu on 2017/2/10.
 */

public class LoginBiz  implements ILoginBiz {

    private static final int LOGIN_SUCCESS = 100;
    private static final int LOGIN_FAILED = 101;

    private OnLoginListener mOnLoginListener;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.arg1){
                case LOGIN_SUCCESS:
                    mOnLoginListener.onSuccess((User) msg.obj);
                    break;
                case LOGIN_FAILED:
                    mOnLoginListener.onFailed((String) msg.obj);
                    break;
            }
        }
    };

    public LoginBiz() {

    }

    @Override
    public void login(final String username, final String pwd, final OnLoginListener onLoginListener) {
        this.mOnLoginListener = onLoginListener;
        //---模拟登录操作
        new  Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    if("yyyu".equals(username) && "123123".equals(pwd)){
                        User user = new User();
                        user.setUsername("yuqibiao");
                        user.setPwd("123123");
                        Message msg = new Message();
                        msg.arg1 = LOGIN_SUCCESS;
                        msg.obj = user;
                        mHandler.sendMessage(msg);
                    }else{
                        Message msg = new Message();
                        msg.arg1 = LOGIN_FAILED;
                        msg.obj = "登录失败！密码错误";
                        mHandler.sendMessage(msg);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void clear(EditText etUsername, EditText etPwd) {
        etUsername.setText("");
        etPwd.setText("");
    }

}
