package com.yyyu.mvptestdemo.di.module;

import com.yyyu.mvptestdemo.presenter.MainPresenter;
import com.yyyu.mvptestdemo.view.MainActivity;
import com.yyyu.mvptestdemo.view.inter.IMainView;

import dagger.Module;
import dagger.Provides;

/**
 * 功能：MainActivity对应的Module
 *
 * Created by yyyu on 2017/2/15.
 */

@Module
public class MainActivityModule {

    private MainActivity mActivity;

    public MainActivityModule(MainActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    MainPresenter provideMainPresenter(IMainView iMainView){
        return new MainPresenter(iMainView);
    }

    @Provides
    IMainView provideIMainView(){
        return mActivity;
    }



}
