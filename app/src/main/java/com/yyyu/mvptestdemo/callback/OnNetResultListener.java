package com.yyyu.mvptestdemo.callback;

/**
 * 功能：得到数据回调
 *
 * Created by yyyu on 2017/2/15.
 */

public interface OnNetResultListener<T> {

    void onSuccess(T data);

    void onFailed(Throwable throwable);

}
