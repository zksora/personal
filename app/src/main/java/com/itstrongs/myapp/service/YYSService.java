package com.itstrongs.myapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.itstrongs.library.helper.Logger;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by itstrongs on 2017/11/25.
 */
public class YYSService extends Service {

    private Runnable mRunnable;
    private Handler mHandler;
    private Random mRandom;

    @Override
    public void onCreate() {
        super.onCreate();
        mRandom = new Random();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
//                execShellCmd("input tap 920 490");
                int x = 920 + mRandom.nextInt(50);
                int y = 490 + mRandom.nextInt(50);
                Logger.d("点击挑战:" + "x=" + x + ",y=" + y);
                execShellCmd("input tap 920 490");
//                Log.d(TAG, "点击准备");
//                execShellCmd("input tap 1100 490");
                mHandler.postDelayed(this, 2000);
            }
        };
        mHandler.postDelayed(mRunnable, 2000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Logger.d("service onDestroy");
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }

    private void execShellCmd(String cmd) {
        try {
            // 申请获取root权限，这一步很重要，不然会没有作用
            Process process = Runtime.getRuntime().exec("su");
            // 获取输出流
            OutputStream outputStream = process.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeBytes(cmd);
            dataOutputStream.flush();
            dataOutputStream.close();
            outputStream.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
