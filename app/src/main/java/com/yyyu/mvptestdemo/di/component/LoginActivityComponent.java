package com.yyyu.mvptestdemo.di.component;

import com.yyyu.mvptestdemo.di.module.LoginActivityModule;

import dagger.Component;

/**
 * 功能：LoginActivity对应的Component
 *
 * Created by yyyu on 2017/2/14.
 */

@Component(dependencies = BaseActivityComponent.class , modules = {LoginActivityModule.class})
public interface LoginActivityComponent {

    //void inject(LoginActivity activity);

}
