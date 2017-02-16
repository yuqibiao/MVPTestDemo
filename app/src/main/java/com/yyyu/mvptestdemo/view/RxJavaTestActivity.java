package com.yyyu.mvptestdemo.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.yyyu.mvptestdemo.R;
import com.yyyu.mvptestdemo.bean.json.GetGithubJson;
import com.yyyu.mvptestdemo.net.ApiManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 功能：RxJava 测试，为了方便查看就不用MVP了
 * <p>
 * 一些名词：
 * Observable ： 被观察者，事件源
 * Subscribers：观察者
 * Operators：操作符，解决对Observable对象的变换的问题
 * <p>
 * 要点：
 * 1.Observable发出一系列事件，Subscribers处理这一系列的事件。
 * 2.一个Observable可以发出一个或者多个时间，每发出一个时间都会调用Subscribers的onNext方法，
 * 最后调用最后调用Subscriber.onNext()或者Subscriber.onError()结束。
 * <p>
 * 好处：
 * 1.错误处理
 * a.只要有异常发生onError()一定会被调用，这极大的简化了错误处理。只需要在一个地方处理错误即可以。
 * b.操作符不需要处理异常，将异常处理交给订阅者来做，Observerable的操作符调用链中一旦有一个抛出
 * 了异常，就会直接执行onError()方法。
 * c.能够知道什么时候订阅者已经接收了全部的数据。
 * 2.调度器：
 * 使用subscribeOn()指定观察者代码运行的线程，使用observerOn()指定订阅者运行的线程
 * 3.订阅：
 * 处理unsubscribing的时候，会停止整个调用链。如果你使用了一串很复杂的操作符，调用unsubscribe将会
 * 在他当前执行的地方终止。不需要做任何额外的工作！
 * <p>
 * 参考：
 * http://blog.csdn.net/lzyzsd/article/details/41833541（一）
 * http://blog.csdn.net/lzyzsd/article/details/44094895（二）
 * http://blog.csdn.net/lzyzsd/article/details/44891933（三）
 * http://blog.csdn.net/lzyzsd/article/details/45033611（四）
 * <p>
 * Created by yyyu on 2017/2/16.
 */

public class RxJavaTestActivity extends BaseActivity {

    private static final String TAG = "RxJavaTestActivity";

    @BindView(R.id.iv_test)
    ImageView ivTest;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_rxjava;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {

        testFoundation();
        testMapOperator();
        testFlatMapOperator();
        testSchedulers();
        testUseInAndroid();
        testUseWithRetrofit();

    }

    /**
     * 结合Retrofit使用
     */
    private void testUseWithRetrofit() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//配置RxJava
                .build();
        ApiManager apiManager = retrofit.create(ApiManager.class);
        apiManager
                .getDithubJsonWithRxJava("Guolei1130")
                .subscribeOn(Schedulers.io())//记得指定网络请求线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetGithubJson>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(RxJavaTestActivity.this, "获取数据失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(GetGithubJson getGithubJson) {
                        Toast.makeText(RxJavaTestActivity.this, "获取数据成功=="+getGithubJson.getLogin(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 在Android中的应用
     */
    private void testUseInAndroid() {

    }

    /**
     * 调度器（指定线程）
     */
    private void testSchedulers() {

        Observable
                .create(new Observable.OnSubscribe<Bitmap>() {
                    @Override
                    public void call(Subscriber<? super Bitmap> subscriber) {
                        Log.e(TAG, "call: ====" + Thread.currentThread());
                        //---人为sleep5s
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        subscriber.onNext(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        Log.e(TAG, "onNext: ====" + Thread.currentThread());
                        ivTest.setImageBitmap(bitmap);
                    }
                });

    }

    /**
     * 测试flatMap操作符
     */
    private void testFlatMapOperator() {

        List<String> testList = new ArrayList<>();
        testList.add("hello");
        testList.add("RxJava");
        testList.add("how");
        testList.add("are");
        testList.add("you");
        testList.add("!!!!!!!!!");
        testList.add("????????");

        //----之前的写法
        Observable
                .just(testList)
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        //此种写法丧失了数据变换的能力
                        for (String str : strings) {
                            //Log.e(TAG, "call: ====" + str);
                        }
                    }
                });
        //---使用flatMap
        //Observable.from() 接收一个集合作为输入，然后每次输出一个元素给subscriber
        Observable
                .just(testList)
                .flatMap(new Func1<List<String>, Observable<String>>() {
                    @Override
                    public Observable<String> call(List<String> strings) {
                        //from接收一个集合作为输入，然后每次输出一个元素给subscriber
                        return Observable.from(strings);
                    }
                })
                .filter(new Func1<String, Boolean>() {//对结果筛选
                    @Override
                    public Boolean call(String s) {
                        return s.length() > 4;
                    }
                })
                .take(3)//最多取3条数据，多于3条就不往下传了
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e(TAG, "flatMap======" + s);
                    }
                });


    }

    /**
     * 测试map操作符
     */
    private void testMapOperator() {

        //---map操作符(转换Observable对象)
        Observable
                .just("原始数据")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "&&&&" + "map操作符添加的数据";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e(TAG, "call: ====map====" + s);
                    }
                });
        Observable//map操作符不必返回原来的类型
                .just("原始数据")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        //map操作符不必返回原来的类型
                        return s.hashCode();
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.e(TAG, "call: map操作符不必返回原来的类型：" + integer);
                    }
                });
        Observable//map操作符可以有多个
                .just("原始数据")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        //map操作符不必返回原来的类型
                        return s.hashCode();
                    }
                })
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return "第二个map操作符" + integer;
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e(TAG, "call: =====" + s);
                    }
                });

    }

    /**
     * RxJava 最基本使用
     */
    private void testFoundation() {
        //----Hello RxJava
        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("Hello RxJava!!!");//Observable发出事件
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted: ==============");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ===========" + e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {//Subscribers.onNext()中接受事件
                        Log.e(TAG, "onNext: ===========" + s);
                    }
                });

        //---简写的 Hello RxJava
        /*
         * RxJava内置了很多简化创建的函数,比如Observable.just就是用来创建只发出一
         * 个事件就结束的Observable对象。当不关心OnComplete和OnError，我们只需
         * 要在onNext的时候做一些处理，这时候就可以使用Action1类。
         */
        Observable
                .just("简写的Hello RxJava")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e(TAG, "call: ======" + s);
                    }
                });

    }

}
