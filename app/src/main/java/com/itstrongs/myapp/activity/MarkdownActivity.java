package com.itstrongs.myapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.itstrongs.library.helper.Logger;
import com.itstrongs.myapp.R;

/**
 * Created by itstrongs on 2017/12/25.
 */
public class MarkdownActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markdown);
        Intent intent = getIntent();
        Logger.d("getIntent().getAction():" + intent.getAction());
        Logger.d("getIntent().getDataString():" + intent.getDataString());
    }
}
