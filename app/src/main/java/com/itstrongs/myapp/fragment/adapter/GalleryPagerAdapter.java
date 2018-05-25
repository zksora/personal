package com.itstrongs.myapp.fragment.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itstrongs on 2017/12/21.
 */
public class GalleryPagerAdapter extends PagerAdapter {

    private List<RecyclerView> mPagerView;
    private List<String> mPagerTitle;

    public GalleryPagerAdapter() {
        this.mPagerView = new ArrayList<>();
        this.mPagerTitle = new ArrayList<>();
    }

    public void addPagerView(List<RecyclerView> pagerView, List<String> pagerTitle) {
        this.mPagerView.addAll(pagerView);
        this.mPagerTitle.addAll(pagerTitle);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPagerTitle.get(position);
    }

    @Override
    public int getCount() {
        return mPagerView.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mPagerView.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mPagerView.get(position));
        return mPagerView.get(position);
    }
}
