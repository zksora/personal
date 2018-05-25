package com.itstrongs.myapp.fragment.plan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.itstrongs.library.helper.Logger;

/**
 * Created by itstrongs on 2017/9/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "youth_plan.db";
    private static final String CREATE_PLAN = "create table plan (" +
            "id text, parent_id text, title text, content text, date text)";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Logger.d("创建数据库");
        db.execSQL(CREATE_PLAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
}
