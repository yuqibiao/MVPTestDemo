package com.yyyu.mvptestdemo.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.yyyu.mvptestdemo.R;
import com.yyyu.mvptestdemo.di.component.DaggerMainActivityComponent;
import com.yyyu.mvptestdemo.di.module.MainActivityModule;
import com.yyyu.mvptestdemo.presenter.MainPresenter;
import com.yyyu.mvptestdemo.view.inter.IMainView;

import javax.inject.Inject;

import butterknife.OnClick;

public class MainActivity extends BaseActivity  implements IMainView{

    @Inject
    MainPresenter mMainPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        DaggerMainActivityComponent
                .builder()
                .baseActivityComponent(getmBaseActCom())
                .mainActivityModule( new MainActivityModule(this))
                .build()
                .inject(this);
    }

    @OnClick({R.id.btn_get, R.id.btn_post, R.id.btn_json})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                mMainPresenter.doGetData("Guolei1130");
                break;
            case R.id.btn_post:
                mMainPresenter.doPostData();
                break;
            case R.id.btn_json:
                mMainPresenter.getJsonData();
                break;
        }
    }

    @Override
    public void showTipToast(String tip) {
        toastUtils.showToast(tip);
    }

    public static void startAction(Activity activity, String username) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("username", username);
        activity.startActivity(intent);
    }
}
