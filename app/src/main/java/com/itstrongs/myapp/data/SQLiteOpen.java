package com.itstrongs.myapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by itstrongs on 2017/11/30.
 */
public class SQLiteOpen extends SQLiteOpenHelper {

    public SQLiteOpen(Context context) {
        super(context, "music.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table music(" +
                "id varchar(32), " +
                "parent_id varchar(32), " +
                "name varchar(20), " +
                "path varchar(50), " +
                "is_music varchar(10), " +
                "child_count varchar(10), " +
                "title varchar(20), " +
                "album varchar(20), " +
                "artist varchar(20), " +
                "time varchar(10), " +
                "duration varchar(20))";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }
}
