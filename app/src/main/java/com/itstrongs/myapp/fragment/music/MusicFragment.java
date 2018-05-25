package com.itstrongs.myapp.fragment.music;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itstrongs.library.base.BaseFragment;
import com.itstrongs.library.base.BasePresenter;
import com.itstrongs.library.helper.Logger;
import com.itstrongs.myapp.R;
import com.itstrongs.myapp.data.PlayerHelper;
import com.itstrongs.myapp.data.bean.Music;
import com.itstrongs.myapp.activity.MainActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by itstrongs on 2017/11/30.
 */
public class MusicFragment extends BaseFragment<MainActivity> {

    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.text_singer)
    TextView textSinger;
    @BindView(R.id.img_play)
    ImageView imgPlay;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private ArrayList<Fragment> mFragments;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_music;
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected void initView() {
        mFragments = new ArrayList<>();
        mFragments.add(new PlayListFragment());
        mFragments.add(new PlayHomeFragment());
        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });
        viewPager.setCurrentItem(1);
    }

    @OnClick({R.id.image_pull, R.id.img_play})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_pull:
                mActivity.switchFragment(MusicFragment.class.getSimpleName());
                break;
            case R.id.img_play:
                imgPlay.setSelected(!imgPlay.isSelected());
                PlayerHelper.getInstance().pauseOrContinue();
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Music currentMusic = PlayerHelper.getInstance().getCurrentMusic(mContext);
        Logger.d("currentMusic:" + currentMusic);
        if (currentMusic != null) {
            textTitle.setText(currentMusic.getTitle());
            textSinger.setText(currentMusic.getArtist());
        }
        imgPlay.setSelected(PlayerHelper.getInstance().getPlayState());
    }

}
