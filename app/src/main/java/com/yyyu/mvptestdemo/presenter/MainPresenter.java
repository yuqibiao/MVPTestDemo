package com.yyyu.mvptestdemo.presenter;

import com.yyyu.mvptestdemo.bean.json.GetGithubJson;
import com.yyyu.mvptestdemo.callback.OnNetResultListener;
import com.yyyu.mvptestdemo.model.MainBiz;
import com.yyyu.mvptestdemo.model.inter.IMainBiz;
import com.yyyu.mvptestdemo.view.inter.IMainView;

/**
 * 功能：MainActivity操作对应的Persenter
 *
 * Created by yyyu on 2017/2/15.
 */

public class MainPresenter {

    private IMainBiz mMainBiz;
    private IMainView mMainView;

    public MainPresenter(IMainView mMainView) {
        this.mMainView = mMainView;
        this.mMainBiz = new MainBiz();
    }

    public void doGetData(String user){
        mMainBiz.getGithubJson(user, new OnNetResultListener<GetGithubJson>() {
            @Override
            public void onSuccess(GetGithubJson data) {
                mMainView.showTipToast("成功==="+data.getLocation());
            }

            @Override
            public void onFailed(Throwable throwable) {
                mMainView.showTipToast("获取服务器数据失败！！");
            }
        });
    }

    public void doPostData(){
        mMainView.showTipToast("doPostData");
    }

    public void getJsonData(){
        mMainView.showTipToast("getJsonData");
    }

}
