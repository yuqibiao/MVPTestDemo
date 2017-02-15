package com.yyyu.mvptestdemo.di.module;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.yyyu.mvptestdemo.presenter.LoginPresenter;
import com.yyyu.mvptestdemo.view.ILoginView;
import com.yyyu.mvptestdemo.view.LoginActivity;

import dagger.Module;
import dagger.Provides;

/**
 * 功能：LoginActivity对应的Module
 *
 * Created by yyyu on 2017/2/14.
 */

@Module
public class LoginActivityModule {

    private LoginActivity mLoginActivity;

    public LoginActivityModule(LoginActivity activity) {
        this.mLoginActivity = activity;
    }

    @Provides
    LoginPresenter provideLoginPresenter(ILoginView loginView){
        return new LoginPresenter(loginView);
    }

    @Provides
    ILoginView provideILoginView(){
        return mLoginActivity;
    }

    @Provides
    ProgressDialog provideProgressDialog(Context context){
        return new ProgressDialog(context);
    }

    @Provides
    Context provideContext(Activity activity){
        return activity;
    }

}
