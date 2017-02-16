package com.yyyu.mvptestdemo.net;

import com.yyyu.mvptestdemo.bean.json.GetGithubJson;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 功能：
 *
 * Created by yyyu on 2017/2/15.
 */

public interface ApiManager {

    @GET("/users/{user}")
    Call<GetGithubJson>  getDithubJson(@Path("user") String user);

    @GET("/users/{user}")
    Observable<GetGithubJson> getDithubJsonWithRxJava(@Path("user") String user);

}
