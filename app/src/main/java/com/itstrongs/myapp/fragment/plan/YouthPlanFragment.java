package com.itstrongs.myapp.fragment.plan;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.itstrongs.library.base.BaseFragment;
import com.itstrongs.myapp.R;
import com.itstrongs.myapp.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by itstrongs on 2017/12/8.
 */
public class YouthPlanFragment extends BaseFragment<MainActivity> {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    private String[] mTitles = new String[]{"计划列表", "时光轴", "完成情况"};
    private List<Fragment> mFragments;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_youth_plan;
    }

    @Override
    protected void initView() {
        mFragments = new ArrayList<>();
        mFragments.add(new PlanTab0Fragment());
        mFragments.add(new PlanTab1Fragment());
        mFragments.add(new PlanTab2Fragment());
        viewPager.setAdapter(new FragmentAdapter(getFragmentManager(), mTitles, mFragments));
        tabLayout.setupWithViewPager(viewPager);
    }

    private class FragmentAdapter extends FragmentStatePagerAdapter {

        private String[] mTitles;
        private List<Fragment> mFragments;

        public FragmentAdapter(FragmentManager fm, String[] titles, List<Fragment> fragments) {
            super(fm);
            this.mTitles = titles;
            this.mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }

}
