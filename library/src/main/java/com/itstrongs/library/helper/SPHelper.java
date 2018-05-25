package com.itstrongs.library.helper;

import android.content.Context;

import com.google.gson.Gson;

/**
 * SharedPreferences helper
 *
 * Created by itstrongs on 2017/11/30.
 */
public class SPHelper {

    public static final String SP_NAME = "sp_name";

    public static void putString(Context context, String key, String value) {
        context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit().putString(key, value).commit();
    }

    public static void putString(Context context, String spName, String key, String value) {
        context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key) {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getString(key, "");
    }

    public static String getString(Context context, String key, String defaultValue) {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getString(key, defaultValue);
    }

    public static String getString(Context context, String spName, String key, String defaultValue) {
        return context.getSharedPreferences(spName, Context.MODE_PRIVATE).getString(key, defaultValue);
    }

    public static void putInt(Context context, String key, int value) {
        context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit().putInt(key, value).commit();
    }

    public static void putInt(Context context, String spName, String key, int value) {
        context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit().putInt(key, value).commit();
    }

    public static int getInt(Context context, String key) {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getInt(key, 0);
    }

    public static int getInt(Context context, String spName, String key) {
        return context.getSharedPreferences(spName, Context.MODE_PRIVATE).getInt(key, 0);
    }

    public static int getInt(Context context, String key, int defaultValue) {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getInt(key, defaultValue);
    }

    public static int getInt(Context context, String spName, String key, int defaultValue) {
        return context.getSharedPreferences(spName, Context.MODE_PRIVATE).getInt(key, defaultValue);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit().putBoolean(key, value).commit();
    }

    public static void putBoolean(Context context, String spName, String key, boolean value) {
        context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key) {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getBoolean(key, false);
    }

    public static boolean getBoolean(Context context, String spName, String key) {
        return context.getSharedPreferences(spName, Context.MODE_PRIVATE).getBoolean(key, false);
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getBoolean(key, defaultValue);
    }

    public static boolean getBoolean(Context context, String spName, String key, boolean defaultValue) {
        return context.getSharedPreferences(spName, Context.MODE_PRIVATE).getBoolean(key, defaultValue);
    }

    public static<T> void putBean(Context context, String key, T value) {
        context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit().putString(key, new Gson().toJson(value)).commit();
    }

    public static<T> void putBean(Context context, String spName, String key, T value) {
        context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit().putString(key, new Gson().toJson(value)).commit();
    }

    public static<T> T getBean(Context context, String key, Class<T> t) {
        return new Gson().fromJson(context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getString(key, ""), t);
    }

    public static<T> T getBean(Context context, String spName, String key, Class<T> t) {
        return new Gson().fromJson(context.getSharedPreferences(spName, Context.MODE_PRIVATE).getString(key, ""), t);
    }
}
