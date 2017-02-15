package com.yyyu.mvptestdemo.model.inter;

import com.yyyu.mvptestdemo.callback.OnNetResultListener;

/**
 * 功能：
 * Created by yyyu on 2017/2/15.
 */

public interface IMainBiz {

    void getGithubJson(String user , OnNetResultListener onNetResultListener);

}
