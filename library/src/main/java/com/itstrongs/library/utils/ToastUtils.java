package com.itstrongs.library.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by itstrongs on 2017/6/10.
 */
public class ToastUtils {

    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}