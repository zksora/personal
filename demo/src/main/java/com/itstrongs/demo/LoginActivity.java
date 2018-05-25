package com.itstrongs.demo;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.itstrongs.library.helper.Logger;

/**
 * Created by itstrongs on 2018/1/3.
 */
public class LoginActivity implements ActivityLife {

    protected AppCompatActivity mActivity;

    @Override
    public void onCreate(AppCompatActivity activity) {
        Logger.d("Proxy onCreate");
        mActivity = activity;
        mActivity.setContentView(R.layout.activity_login);
        TextView textView = (TextView) mActivity.findViewById(R.id.text_view);
        textView.setText("登录2");
    }

    @Override
    public void onResume() {
        Logger.d("Proxy onResume");
    }

    @Override
    public void onDestroy() {
        Logger.d("Proxy onDestroy");
    }
}