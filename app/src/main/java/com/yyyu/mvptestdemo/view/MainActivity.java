package com.yyyu.mvptestdemo.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.yyyu.mvptestdemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String username = getIntent().getStringExtra("username");
        TextView tvInfo = (TextView) findViewById(R.id.tv_info);
        tvInfo.setText("用户名是："+username);
    }

    public static void startAction(Activity activity  , String username){
        Intent intent = new Intent(activity , MainActivity.class);
        intent.putExtra("username" , username);
        activity.startActivity(intent);
    }

}
