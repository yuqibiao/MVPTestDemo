package com.yyyu.mvptestdemo.di.component;

import android.app.Activity;

import com.yyyu.mvptestdemo.di.module.BaseActivityModule;
import com.yyyu.mvptestdemo.utils.ToastUtil;

import dagger.Component;

/**
 * 功能：
 * Created by yyyu on 2017/2/14.
 */

@Component(dependencies = ApplicationComponent.class , modules = {BaseActivityModule.class})
public interface BaseActivityComponent {

    //向子类Activity提供ToastUtils实例
    ToastUtil proToastUtils();

    //向子类提供Activity实例
    Activity proActivity();


}
