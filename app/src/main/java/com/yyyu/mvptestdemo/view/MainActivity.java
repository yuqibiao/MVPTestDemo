package com.yyyu.mvptestdemo.view;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import com.yyyu.mvptestdemo.R;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_info)
    TextView tvInfo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        String username = getIntent().getStringExtra("username");
        tvInfo.setText("用户名是：" + username);
    }

    public static void startAction(Activity activity, String username) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("username", username);
        activity.startActivity(intent);
    }

}
