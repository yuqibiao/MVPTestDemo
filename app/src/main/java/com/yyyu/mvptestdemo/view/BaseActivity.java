package com.yyyu.mvptestdemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yyyu.mvptestdemo.MyApplication;
import com.yyyu.mvptestdemo.di.component.BaseActivityComponent;
import com.yyyu.mvptestdemo.di.component.DaggerBaseActivityComponent;
import com.yyyu.mvptestdemo.di.module.BaseActivityModule;

import butterknife.ButterKnife;

/**
 * 功能：基类Activity
 *
 * Created by yyyu on 2017/2/14.
 */

public abstract  class BaseActivity extends AppCompatActivity{

    private BaseActivityComponent mBaseActCom;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
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
                .baseActivityModule(new BaseActivityModule())
                .build();
    }

    protected abstract void initView();

    public BaseActivityComponent getmBaseActCom() {
        return mBaseActCom;
    }
}
