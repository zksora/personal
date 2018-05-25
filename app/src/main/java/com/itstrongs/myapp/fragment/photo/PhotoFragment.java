package com.itstrongs.myapp.fragment.photo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.itstrongs.library.base.BaseFragment;
import com.itstrongs.myapp.R;
import com.itstrongs.myapp.activity.MainActivity;
import com.itstrongs.myapp.data.JsoupHelper;
import com.itstrongs.myapp.data.bean.Gallery;
import com.itstrongs.myapp.data.bean.Gallery2;
import com.itstrongs.myapp.fragment.photo.girl.GirlFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by itstrongs on 2017/12/11.
 */
public class PhotoFragment extends BaseFragment<MainActivity> {

    @BindView(R.id.tab_layout_photo_2)
    TabLayout tabLayout;
    @BindView(R.id.view_pager_photo_2)
    ViewPager viewPager;

    private List<Gallery2> mGalleries;
    private FragmentAdapter mAdapter;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_photo;
    }

    @Override
    protected void initView() {
        mGalleries = new ArrayList<>();
        viewPager.setAdapter(mAdapter = new FragmentAdapter(getFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                mActivity.setLittleTitle(mGalleries.get(position).getDescription());
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });
        tabLayout.setupWithViewPager(viewPager);}

    @Override
    protected void initData() {
        JsoupHelper.getInstance().doGetPhotoList(new Consumer<List<Gallery>>() {
            @Override
            public void accept(List<Gallery> galleries) throws Exception {
                for (Gallery gallery : galleries) {
                    Gallery2 gallery2 = new Gallery2();
                    gallery2.setTitle(gallery.getName());
                    gallery2.setDescription(gallery.getDescription());
                    gallery2.setFragment(new PictureFragment(gallery));
                    mGalleries.add(gallery2);
                }
                mGalleries.add(new Gallery2("妹纸福利", "妹子福利图片...", new GirlFragment()));
                mGalleries.add(new Gallery2("害虫影集", "害虫图片...", new QiFragment()));
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private class FragmentAdapter extends FragmentStatePagerAdapter {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mGalleries.get(position).getFragment();
        }

        @Override
        public int getCount() {
            return mGalleries.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mGalleries.get(position).getTitle();
        }
    }
}
