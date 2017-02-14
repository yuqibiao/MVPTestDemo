package com.yyyu.mvptestdemo.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * 功能：MyApplication对应module
 *
 * Created by yyyu on 2017/2/14.
 */

@Module
public class ApplicationModule {

    private Context mContext;

    public ApplicationModule(Context context){
        this.mContext = context;
    }

    @Provides
    public Context provideContext(){

        return mContext;
    }

}
