package com.itstrongs.demo;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by itstrongs on 2018/1/3.
 */
public interface ActivityLife {

    void onCreate(AppCompatActivity activity);

    void onResume();

    void onDestroy();
}
