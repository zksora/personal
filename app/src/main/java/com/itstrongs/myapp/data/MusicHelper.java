package com.itstrongs.myapp.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itstrongs.library.helper.Logger;
import com.itstrongs.library.helper.SPHelper;
import com.itstrongs.library.utils.ToastUtils;
import com.itstrongs.myapp.data.bean.Music;
import com.itstrongs.myapp.fragment.music.MusicContract;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.itstrongs.myapp.data.ConstantPool.SP_IS_LOAD_DATA;
import static com.itstrongs.myapp.data.ConstantPool.SP_MUSIC_PATH;
import static com.itstrongs.myapp.data.ConstantPool.SP_PLAY_LIST;

/**
 * 音乐数据处理
 *
 * Created by itstrongs on 2017/11/16.
 */
public class MusicHelper implements MusicContract.Data {

    private static MusicHelper mInstance;

    private SQLiteOpen mOpenHelper;
    private List<Music> mMusicData;     //当前文件夹音乐数据
    private Music mParentMusic;
    private List<Music> mPlayList;      //播放列表

    public boolean mIsLoading;
    public String mRootDir;            //音乐根目录

    private MusicHelper() {
        mMusicData = new ArrayList<>();
    }

    public static MusicHelper getInstance() {
        if (mInstance == null) {
            mInstance = new MusicHelper();
        }
        return mInstance;
    }

    public void initMusic(Context context) {
        mOpenHelper = new SQLiteOpen(context);
        mRootDir = SPHelper.getString(context, SP_MUSIC_PATH);
        if (mRootDir.isEmpty()) {
            ToastUtils.show(context, "请先选择音乐目录");
            return;
        }
        mPlayList = SPHelper.getBean(context, SP_PLAY_LIST, List.class);
        if (mPlayList == null) {
            mPlayList = new ArrayList<>();
        }
    }

    public void loadMusicData(final Context context, File file) {
        mRootDir = file.getPath();
        Logger.d("选中的目录：" + mRootDir);
        SPHelper.putString(context, SP_MUSIC_PATH, mRootDir);
        new Thread(new Runnable() {
            @Override
            public void run() {
                mIsLoading = true;
                Logger.d("子线程加载音乐数据");
                File rootFile = new File(mRootDir);
                deleteDatabase();
                addChild(rootFile, "0");
                SPHelper.putBoolean(context, SP_IS_LOAD_DATA, true);
                mIsLoading = false;
            }
        }).start();
    }

    private void addChild(File parentFile, String parentId) {
        File[] files = parentFile.listFiles();
        if (files != null) {
            for (File file : files) {
                String name = file.getName();
                String substring = null;
                if (!file.isDirectory()) {
                    substring = name.substring(name.lastIndexOf(".") + 1, name.length());
                }
                if ("mp3".equals(substring) || file.isDirectory() || "wma".equals(substring)) {
                    String id = UUID.randomUUID().toString();
                    Music music = new Music();
                    music.setId(id);
                    music.setParentId(parentId);
                    music.setName(name);
                    music.setPath(file.getPath());
                    music.setMusic(!file.isDirectory());
                    if (file.isDirectory()) {
                        music.setChildCount(file.listFiles().length);
                    } else {
                        PlayerHelper.getInstance().getMusicInfo(file.getPath(), music);
                    }
                    addDatabase(music);
                    if (file.isDirectory())
                        addChild(file, id);
                }
            }
        }
    }

    @Override
    public List<Music> loadHomeData() {
        mMusicData = findDatabase("0");
        return mMusicData;
    }

    @Override
    public List<Music> loadNextData(int position) {
        Music nextMusic = mMusicData.get(position);
        if (nextMusic.isMusic()) {
            PlayerHelper.getInstance().play(nextMusic);
            mPlayList = mMusicData;
            return null;
        } else {
            mParentMusic = nextMusic;
            mMusicData.clear();
            mMusicData = findDatabase(nextMusic.getId());
            Logger.d("下一页数据：" + mMusicData.toString());
        }
        return mMusicData;
    }

    @Override
    public List<Music> loadLastData() {
        if (mParentMusic == null || mRootDir.equals(mParentMusic.getName())) {
            return null;
        }
        Logger.d("loadLastData:" + mParentMusic.getName());
        mMusicData.clear();
        mMusicData = findDatabase(mParentMusic.getParentId());
        mParentMusic = findMusicDatabase(mParentMusic.getParentId());
        Logger.d("上一页数据");
        return mMusicData;
    }

    @Override
    public String loadMusicPath() {
        String currentPath = "岁月如歌";
        if (mParentMusic != null) {
            String path = mParentMusic.getPath();
            String homePath = path.substring(path.lastIndexOf("岁月如歌"), path.length());
            Logger.d("homePath:" + homePath);
            String[] split = homePath.split("/");
            for (int i = 0; i < split.length; i++) {
                if (i != 0) currentPath += (" > " + split[i]);
            }
        }
        return currentPath;
    }

    private Music findMusicDatabase(String id) {
        if ("0".equals(id)) return null;
        Music music = new Music();
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from music where id = ?", new String[] { id });
        while (cursor.moveToNext()) {
            music = new Music();
            music.setId(cursor.getString(cursor.getColumnIndex("id")));
            music.setParentId(cursor.getString(cursor.getColumnIndex("parent_id")));
            music.setName(cursor.getString(cursor.getColumnIndex("name")));
            music.setPath(cursor.getString(cursor.getColumnIndex("path")));
            music.setMusic("true".equals(cursor.getString(cursor.getColumnIndex("is_music"))));
            music.setChildCount(Integer.parseInt(cursor.getString(cursor.getColumnIndex("child_count"))));
            music.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            music.setAlbum(cursor.getString(cursor.getColumnIndex("album")));
            music.setArtist(cursor.getString(cursor.getColumnIndex("artist")));
            music.setTime(cursor.getString(cursor.getColumnIndex("time")));
            music.setDuration(cursor.getString(cursor.getColumnIndex("duration")));
        }
        cursor.close();
        db.close();
        return music;
    }

    public void addDatabase(Music music) {
        Logger.d("add " + music.getName());
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String sql = "insert into music(id, parent_id, name, path, is_music, " +
                "child_count, title, album, artist, time, duration) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        db.execSQL(sql, new String[] {
                music.getId(),
                music.getParentId(),
                music.getName(),
                music.getPath(),
                music.isMusic() + "",
                music.getChildCount() + "",
                music.getTitle(),
                music.getAlbum(),
                music.getArtist(),
                music.getTime(),
                music.getDuration()
        });
        db.close();
    }

    public List<Music> findDatabase(String parentId) {
        List<Music> musics = new ArrayList<>();
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from music where parent_id = ?", new String[]{ parentId });

        while (cursor.moveToNext()) {
            Music music = new Music();
            music.setId(cursor.getString(cursor.getColumnIndex("id")));
            music.setParentId(cursor.getString(cursor.getColumnIndex("parent_id")));
            music.setName(cursor.getString(cursor.getColumnIndex("name")));
            music.setPath(cursor.getString(cursor.getColumnIndex("path")));
            music.setMusic("true".equals(cursor.getString(cursor.getColumnIndex("is_music"))));
            music.setChildCount(Integer.parseInt(cursor.getString(cursor.getColumnIndex("child_count"))));
            music.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            music.setAlbum(cursor.getString(cursor.getColumnIndex("album")));
            music.setArtist(cursor.getString(cursor.getColumnIndex("artist")));
            music.setTime(cursor.getString(cursor.getColumnIndex("time")));
            music.setDuration(cursor.getString(cursor.getColumnIndex("duration")));
            musics.add(music);
        }
        cursor.close();
        db.close();
        return musics;
    }

    public void deleteDatabase() {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.execSQL("delete from music");
        db.close();
    }

    public List<Music> getPlayList() {
        return mPlayList;
    }

}
