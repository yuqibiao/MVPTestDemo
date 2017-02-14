package com.yyyu.mvptestdemo;

import android.app.Application;

import com.yyyu.mvptestdemo.di.component.ApplicationComponent;
import com.yyyu.mvptestdemo.di.component.DaggerApplicationComponent;
import com.yyyu.mvptestdemo.di.module.ApplicationModule;

/**
 * 功能：自定义Application（放全局变量）
 *
 * Created by yyyu on 2017/2/14.
 */

public class MyApplication extends Application{

    private ApplicationComponent mAppCom;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化ApplicationComponent
        mAppCom = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getmAppCom() {
        return mAppCom;
    }
}
