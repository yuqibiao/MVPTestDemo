package com.yyyu.mvptestdemo.model;

import com.yyyu.mvptestdemo.bean.json.GetGithubJson;
import com.yyyu.mvptestdemo.callback.OnNetResultListener;
import com.yyyu.mvptestdemo.model.inter.IMainBiz;
import com.yyyu.mvptestdemo.net.ApiManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 功能：
 * Created by yyyu on 2017/2/15.
 */

public class MainBiz implements IMainBiz{


    @Override
    public void getGithubJson(String user , final OnNetResultListener onNetResultListener) {

       Retrofit retrofit = new Retrofit
               .Builder()
               .baseUrl("https://api.github.com/")
               .addConverterFactory(GsonConverterFactory.create())
               .build();

        ApiManager apiManager = retrofit.create(ApiManager.class);
        Call<GetGithubJson> call = apiManager.getDithubJson(user);
        call.enqueue(new Callback<GetGithubJson>() {
            @Override
            public void onResponse(Call<GetGithubJson> call, Response<GetGithubJson> response) {
                onNetResultListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<GetGithubJson> call, Throwable t) {
                onNetResultListener.onFailed(t);
            }
        });

    }


}
