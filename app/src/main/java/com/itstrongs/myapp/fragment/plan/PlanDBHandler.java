package com.itstrongs.myapp.fragment.plan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itstrongs.myapp.data.bean.Plan;

import java.util.ArrayList;
import java.util.List;

/**
 * 计划增删改查处理器
 * Created by itstrongs on 2017/9/15.
 */
public class PlanDBHandler {

    private static PlanDBHandler mHandler;
    private static SQLiteDatabase mDatabase;

    public static PlanDBHandler getInstance(Context context) {
        if (mHandler == null) {
            mHandler = new PlanDBHandler();
            DatabaseHelper helper = new DatabaseHelper(context);
            mDatabase = helper.getWritableDatabase();
        }
        return mHandler;
    }

    public void doAddPlan(Plan plan) {
        mDatabase.execSQL("insert into plan(id, parent_id, title, content, date) values(?, ?, ?, ?, ?)",
                new String[]{ plan.getId(), plan.getParentId(),
                        plan.getTitle(), plan.getContent(), plan.getCreateDate() });
    }

    // 查看数据库里的所有数据
    public List<Plan> doQueryPlanList() {
        List<Plan> planList = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("select * from plan", null);
        while (cursor.moveToNext()) {
            if ("0".equals(cursor.getColumnIndex("id"))) break;
            Plan planBean = new Plan();
            planBean.setId(cursor.getString(cursor.getColumnIndex("id")));
            planBean.setParentId(cursor.getString(cursor.getColumnIndex("parent_id")));
            planBean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            planBean.setCreateDate(cursor.getString(cursor.getColumnIndex("date")));
            planBean.setContent(cursor.getString(cursor.getColumnIndex("content")));
            planList.add(planBean);
        }
        cursor.close();
        return planList;
    }

    // 递归查询所有数据存到计划树结构中
    public Plan doQueryPlanTree(String id) {
        Plan planBean = new Plan();     //节点id为0的父节点
        Cursor cursor = mDatabase.rawQuery("select * from plan where plan.id = ?", new String[]{ id });
        if (!cursor.moveToNext()) return null;
        planBean.setId(cursor.getString(cursor.getColumnIndex("id")));
        planBean.setParentId(cursor.getString(cursor.getColumnIndex("parent_id")));
        planBean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
        planBean.setCreateDate(cursor.getString(cursor.getColumnIndex("date")));
        planBean.setContent(cursor.getString(cursor.getColumnIndex("content")));
        cursor.close();
        Cursor childCursor = mDatabase.rawQuery("select * from plan where parent_id = ?", new String[]{ id });
        while (childCursor.moveToNext()) {
            Plan childPlan = doQueryPlanTree(childCursor.getString(cursor.getColumnIndex("id")));
            planBean.getChildPlan().add(childPlan);
        }
        childCursor.close();
        return planBean;
    }
}
