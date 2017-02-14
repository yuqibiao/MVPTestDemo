package com.yyyu.mvptestdemo.di.module;

import com.yyyu.mvptestdemo.presenter.LoginPresenter;
import com.yyyu.mvptestdemo.view.ILoginView;

import dagger.Module;
import dagger.Provides;

/**
 * 功能：LoginActivity对应的Module
 *
 * Created by yyyu on 2017/2/14.
 */

@Module
public class LoginActivityModule {

    private ILoginView mLoginView;

    public LoginActivityModule(ILoginView mLoginView) {
        this.mLoginView = mLoginView;
    }

    @Provides
    LoginPresenter provideLoginPresenter(ILoginView loginView){
        return new LoginPresenter(loginView);
    }

    @Provides
    ILoginView provideILoginView(){
        return mLoginView;
    }

}
