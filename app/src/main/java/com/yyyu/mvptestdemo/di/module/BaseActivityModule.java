package com.yyyu.mvptestdemo.di.module;

import android.content.Context;

import com.yyyu.mvptestdemo.utils.ToastUtil;

import dagger.Module;
import dagger.Provides;

/**
 * 功能：BaseActivity对应的Module
 *
 * Created by yyyu on 2017/2/14.
 */

@Module
public class BaseActivityModule {

    @Provides
    ToastUtil provideToastUtils(Context context){

        return new ToastUtil(context);
    }

}
