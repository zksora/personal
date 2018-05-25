package com.itstrongs.myapp.data.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 计划bean
 * Created by itstrongs on 2017/9/15.
 */
public class Plan {

    private String id;
    private String parentId;
    private String title;
    private String content;
    private String createDate;      //创建时间
    private String alterDate;       //最近修改时间
    private String state;           //计划进行状态
    private String executeTime;     //计划已执行时间
    private String category;        //所属分类
    private String signin;          //打卡签到
    private List<Plan> childPlan = new ArrayList<>();          //打卡签到

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getAlterDate() {
        return alterDate;
    }

    public void setAlterDate(String alterDate) {
        this.alterDate = alterDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSignin() {
        return signin;
    }

    public void setSignin(String signin) {
        this.signin = signin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<Plan> getChildPlan() {
        return childPlan;
    }

    public void setChildPlan(List<Plan> childPlan) {
        this.childPlan = childPlan;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate='" + createDate + '\'' +
                ", alterDate='" + alterDate + '\'' +
                ", state='" + state + '\'' +
                ", executeTime='" + executeTime + '\'' +
                ", category='" + category + '\'' +
                ", signin='" + signin + '\'' +
                ", childPlan=" + childPlan +
                '}';
    }
}
