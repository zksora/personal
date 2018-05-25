package com.itstrongs.library.http;

import android.content.Context;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by itstrongs on 2017/12/28.
 */
public class NovateCookieManger implements CookieJar {

    private static final String TAG = "NovateCookieManger";
    private static Context mContext;
    private static PersistentCookieStore cookieStore;

    public NovateCookieManger(Context context) {
        mContext = context;
        if (cookieStore == null) {
            cookieStore = new PersistentCookieStore(mContext);
        }
    }

    @Override
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {

    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        return null;
    }
}
