package com.itstrongs.library.helper;

import android.util.Log;

/**
 * Created by itstrongs on 2017/12/28.
 */
public class Logger {

    public static String TAG = "logger_";

    public static boolean mIsOpen = true;

    public static void d(String msg) {
        if (mIsOpen) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (mIsOpen) {
            Log.i(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (mIsOpen) {
            Log.w(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (mIsOpen) {
            Log.e(TAG, msg);
        }
    }
}
