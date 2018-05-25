package com.itstrongs.myapp.data;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;

import com.itstrongs.library.helper.Logger;
import com.itstrongs.library.helper.SPHelper;
import com.itstrongs.myapp.data.bean.Music;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static com.itstrongs.myapp.data.ConstantPool.SP_CURRENT_MUSIC;

/**
 * 音乐播放数据处理
 *
 * Created by itstrongs on 2017/11/29.
 */
public class PlayerHelper {

    private static PlayerHelper mInstance;

    private Timer mTimer;
    private TimerTask mTask;
    private MediaPlayer mPlayer;
    private Music mCurrentMusic;
    private MediaMetadataRetriever mRetriever;
    private OnProgressListener mProgressListener;

    private PlayerHelper() {
        mTimer = new Timer();
        mPlayer = new MediaPlayer();
        mRetriever = new MediaMetadataRetriever();
        mPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                if (mPlayer.isPlaying()) {
                    mPlayer.start();
                }
            }
        });
    }

    public static PlayerHelper getInstance() {
        if (mInstance == null) {
            mInstance = new PlayerHelper();
        }
        return mInstance;
    }

    public void play(Music music) {
        Logger.d("播放音乐：" + music);
        if (mCurrentMusic != null && mCurrentMusic.equals(music))
            return;
        if (mPlayer.isPlaying()) {
            mPlayer.stop();
        }
        mPlayer.reset();
        try {
            mPlayer.setDataSource(music.getPath());
            mPlayer.prepare();
            mPlayer.setLooping(false);
            mPlayer.start();
            mCurrentMusic = music;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pauseOrContinue() {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        } else {
            mPlayer.start();
        }
    }

    public void getMusicInfo(String path, Music music) {
        mRetriever.setDataSource(path);
        String title = mRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        if (title == null || title.isEmpty()) {
            String name = music.getName();
            music.setTitle(name.substring(0, name.lastIndexOf(".")));
        } else {
            music.setTitle(title);
        }
        music.setAlbum(mRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM));
        String artist = mRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        if (artist == null || artist.isEmpty()) {
            music.setArtist("未知歌手");
        } else {
            music.setArtist(artist);
        }
        int time = Integer.parseInt(mRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
        music.setTime(time + "");
        music.setDuration("0" + (time / 1000 / 60) + ":" + ((time / 1000 % 60)));
        music.setPic(mRetriever.getEmbeddedPicture());
    }

    public Music getCurrentMusic(Context context) {
        if (mCurrentMusic == null) {
            mCurrentMusic = SPHelper.getBean(context, SP_CURRENT_MUSIC, Music.class);
            if (mCurrentMusic != null) {
                mPlayer.reset();
                try {
                    mPlayer.setDataSource(mCurrentMusic.getPath());
                    mPlayer.prepare();
                    mPlayer.setLooping(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return mCurrentMusic;
    }

    public void saveMusicData(Context context) {
        if (mCurrentMusic != null) {
            Logger.d("保存的音乐数据：" + mCurrentMusic);
            SPHelper.putBean(context, SP_CURRENT_MUSIC, mCurrentMusic);
        }
    }

    public boolean getPlayState() {
        return mPlayer.isPlaying();
    }

    public void setProgress(int progress) {
        mPlayer.seekTo(progress * 1000);
    }

    public void setOnProgressListener(OnProgressListener listener) {
        this.mProgressListener = listener;
        mTask = new TimerTask() {
            @Override
            public void run() {
                if (mPlayer.isPlaying()) {
                    mProgressListener.onProgress(mPlayer.getCurrentPosition() / 1000);
                }
            }
        };
        mTimer.schedule(mTask, 1000, 1000);
    }

    public interface OnProgressListener {

        void onProgress(int progress);
    }

}
