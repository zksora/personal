package com.itstrongs.myapp.fragment.tools;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.itstrongs.library.base.BaseFragment;
import com.itstrongs.myapp.R;
import com.itstrongs.myapp.activity.MainActivity;
import com.itstrongs.myapp.fragment.adapter.BaseFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by itstrongs on 2017/12/11.
 */
public class ToolsFragment extends BaseFragment<MainActivity> {

    @BindView(R.id.tab_layout_tools)
    TabLayout tabLayout;
    @BindView(R.id.view_pager_tools)
    ViewPager viewPager;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_tools;
    }

    @Override
    protected void initView() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new YYSFragment());
        fragments.add(new RedPackFragment());
        fragments.add(new AlarmFragment());
        viewPager.setAdapter(new BaseFragmentAdapter(getFragmentManager(),
                getResources().getStringArray(R.array.array_tools), fragments));
        tabLayout.setupWithViewPager(viewPager);
    }

}
