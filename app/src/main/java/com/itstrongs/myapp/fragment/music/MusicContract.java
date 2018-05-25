package com.itstrongs.myapp.fragment.music;

import android.content.Context;

import com.itstrongs.library.base.BasePresenter;
import com.itstrongs.library.base.BaseView;
import com.itstrongs.myapp.data.bean.Music;

import java.util.List;

/**
 * Created by itstrongs on 2017/11/29.
 */
public interface MusicContract {

    interface View extends BaseView<Presenter> {

        void showMusicList(List<Music> musicList);

        void showPlayView();

        Context getContext();
    }

    interface Presenter extends BasePresenter {

        void doHomeMusic();

        void doNextMusic(int position);

        void doLastMusic();

        String doGetMusicPath();
    }

    interface Data {

        List<Music> loadHomeData();                //加载首页数据

        List<Music> loadNextData(int position);    //加载下一页数据

        List<Music> loadLastData();                //加载上一页数据

        String loadMusicPath();
    }

    interface ResultCallback<T> {

        void onResult(T t);

    }

}
