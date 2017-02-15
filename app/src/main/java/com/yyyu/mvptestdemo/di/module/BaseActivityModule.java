package com.yyyu.mvptestdemo.di.module;

import android.app.Activity;
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

    private Activity mActivity;

    public BaseActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    ToastUtil provideToastUtils(Context context){
        return new ToastUtil(context);
    }

    @Provides
    Activity provideActivity(){
        return mActivity;
    }

}
