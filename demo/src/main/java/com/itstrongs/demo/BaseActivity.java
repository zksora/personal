package com.itstrongs.demo;

import android.support.v7.app.AppCompatActivity;

import com.itstrongs.library.helper.Logger;

/**
 * Created by itstrongs on 2018/1/3.
 */
public abstract class BaseActivity implements ActivityLife {

    protected AppCompatActivity mActivity;

    @Override
    public void onCreate(AppCompatActivity activity) {
        Logger.d("Proxy onCreate");
        mActivity = activity;
        mActivity.setContentView(getContentView());
        initView();
    }

    @Override
    public void onResume() {
        Logger.d("Proxy onResume");
    }

    @Override
    public void onDestroy() {
        Logger.d("Proxy onDestroy");
    }

    abstract protected int getContentView();

    abstract protected void initView();
}