package com.yyyu.mvptestdemo.di.component;

import com.yyyu.mvptestdemo.di.module.MainActivityModule;
import com.yyyu.mvptestdemo.view.MainActivity;

import dagger.Component;

/**
 * 功能：MainActivity对应的Component
 *
 * Created by yyyu on 2017/2/15.
 */

@Component(dependencies = BaseActivityComponent.class , modules = {MainActivityModule.class})
public interface MainActivityComponent {

    void inject(MainActivity activity);

}
