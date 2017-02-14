package com.yyyu.mvptestdemo.di.component;

import android.content.Context;

import com.yyyu.mvptestdemo.di.module.ApplicationModule;

import dagger.Component;

/**
 * 功能：
 * Created by yyyu on 2017/2/14.
 */

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    //向下提供Context
     Context proContext();
}
