package com.yyyu.mvptestdemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yyyu.mvptestdemo.MyApplication;
import com.yyyu.mvptestdemo.di.component.BaseActivityComponent;
import com.yyyu.mvptestdemo.di.component.DaggerBaseActivityComponent;
import com.yyyu.mvptestdemo.di.module.BaseActivityModule;
import com.yyyu.mvptestdemo.utils.ToastUtil;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 功能：基类Activity
 *
 * Created by yyyu on 2017/2/14.
 */

public abstract  class BaseActivity extends AppCompatActivity{

    @Inject
    protected ToastUtil toastUtils;

    private BaseActivityComponent mBaseActCom;
    private Unbinder mUnbind;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbind = ButterKnife.bind(this);
        init();
    }

    protected abstract  int getLayoutId();
    
    private void init() {
        initBase();
        initView();
    }

    private void initBase() {
        mBaseActCom = DaggerBaseActivityComponent
                .builder()
                .applicationComponent( ( (MyApplication) getApplication() ) .getmAppCom())
                .baseActivityModule(new BaseActivityModule(this))
                .build();
    }

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbind.unbind();
    }

    public BaseActivityComponent getmBaseActCom() {
        return mBaseActCom;
    }
}
