package com.itstrongs.myapp.data.bean;

import android.support.v4.app.Fragment;

/**
 * 画廊（影集）
 *
 * Created by itstrongs on 2018/1/5.
 */
public class Gallery2 {

    private String title;
    private String childUrl;
    private Fragment fragment;
    private String description;

    public Gallery2() { }

    public Gallery2(String title, String description, Fragment fragment) {
        this.title = title;
        this.description = description;
        this.fragment = fragment;
    }

    public String getChildUrl() {
        return childUrl;
    }

    public void setChildUrl(String childUrl) {
        this.childUrl = childUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
