package com.itstrongs.myapp.fragment.music;

import com.itstrongs.library.utils.ToastUtils;
import com.itstrongs.myapp.data.MusicHelper;
import com.itstrongs.myapp.data.bean.Music;

import java.util.List;

/**
 * Created by itstrongs on 2017/11/29.
 */
public class MusicPresenter implements MusicContract.Presenter {

    private MusicContract.View mMusicView;
    private MusicContract.Data mMusicData;

    public MusicPresenter(MusicContract.View view) {
        mMusicView = view;
        view.setPresenter(this);
        mMusicData = MusicHelper.getInstance();
    }

    @Override
    public void start() {
        doHomeMusic();
    }

    @Override
    public void doHomeMusic() {
        if (MusicHelper.getInstance().mIsLoading) {
            ToastUtils.show(mMusicView.getContext(), "正在加载音乐，请稍后");
        } else if (MusicHelper.getInstance().mRootDir.isEmpty()) {
            ToastUtils.show(mMusicView.getContext(), "请先选择音乐目录");
        } else {
            mMusicView.showMusicList(mMusicData.loadHomeData());
        }
    }

    @Override
    public void doNextMusic(int position) {
        List<Music> nextData = mMusicData.loadNextData(position);
        if (nextData == null) {
            mMusicView.showPlayView();
        } else {
            mMusicView.showMusicList(nextData);
        }
    }

    @Override
    public void doLastMusic() {
        mMusicView.showMusicList(mMusicData.loadLastData());
    }

    @Override
    public String doGetMusicPath() {
        return mMusicData.loadMusicPath();
    }

}
