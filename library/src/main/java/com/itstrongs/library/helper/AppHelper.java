package com.itstrongs.library.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by itstrongs on 2017/6/22.
 */
public class AppHelper {

    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }
}