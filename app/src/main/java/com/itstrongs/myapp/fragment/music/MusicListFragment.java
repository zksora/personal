package com.itstrongs.myapp.fragment.music;

import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itstrongs.library.base.BaseAdapter;
import com.itstrongs.library.base.BaseFragment;
import com.itstrongs.library.base.BasePresenter;
import com.itstrongs.library.helper.Logger;
import com.itstrongs.myapp.R;
import com.itstrongs.myapp.data.MusicHelper;
import com.itstrongs.myapp.data.PlayerHelper;
import com.itstrongs.myapp.data.bean.Music;
import com.itstrongs.myapp.activity.MainActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.itstrongs.myapp.R.id.text_title;

/**
 * Created by itstrongs on 2017/11/29.
 */
public class MusicListFragment extends BaseFragment<MainActivity> implements MusicContract.View {

    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.text_view)
    TextView textView;
    @BindView(R.id.text_path)
    TextView textPath;
    @BindView(R.id.img_play)
    TextView imgPlay;
    @BindView(text_title)
    TextView textTitle;
    @BindView(R.id.text_singer)
    TextView textSinger;

    private MyAdapter mMusicAdapter;
    private MusicContract.Presenter mPresenter;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_music_list;
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return new MusicPresenter(this);
    }

    @Override
    public void setPresenter(MusicContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void initView() {
        recycleView.setLayoutManager(new LinearLayoutManager(mContext));
        recycleView.setAdapter(mMusicAdapter = new MyAdapter());
        mMusicAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                mPresenter.doNextMusic(position);
            }
        });
        MusicHelper.getInstance().initMusic(mContext);
        Music music = PlayerHelper.getInstance().getCurrentMusic(mContext);
        if (music != null) {
            textTitle.setText(music.getTitle());
            textSinger.setText(music.getArtist());
        }
    }

    @Override
    public void showMusicList(List<Music> musicList) {
        if (musicList != null) {
            textPath.setText(mPresenter.doGetMusicPath());
            mMusicAdapter.setMusicData(musicList);
            mMusicAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showPlayView() {
        mActivity.switchFragment(MusicFragment.class.getSimpleName());
    }

    @OnClick({R.id.button, R.id.text_last, R.id.img_play, R.id.linear_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                showFileChooseDialog();
                break;
            case R.id.text_last:
                mPresenter.doLastMusic();
                break;

            case R.id.img_play:
                imgPlay.setSelected(!imgPlay.isSelected());
                PlayerHelper.getInstance().pauseOrContinue();
                break;
            case R.id.linear_layout:
                mActivity.switchFragment(MusicFragment.class.getSimpleName());
                break;
        }
    }

    private File lastFile;
    private List<String> fileName;
    private DialogAdapter mAdapter;

    private void showFileChooseDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_file_choose, null);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        lastFile = Environment.getExternalStorageDirectory();
        fileName = new ArrayList<>();
        setFileData(lastFile);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(mAdapter = new DialogAdapter(fileName));
        mAdapter.setOnClickListener(new OnClickListener() {
            @Override
            public void clickListener(int position, int resId) {
                switch (resId) {
                    case R.id.layout:
                        String lastName = fileName.get(position);
                        if ("..".equals(lastName)) {
                            lastFile = lastFile.getParentFile();
                        } else {
                            lastFile = new File(lastFile, lastName);
                        }
                        setFileData(lastFile);
                        mAdapter.setDataList(fileName);
                        mAdapter.notifyDataSetChanged();
                        break;
                    case R.id.image_choose:
                        MusicHelper.getInstance().loadMusicData(mContext, new File(lastFile, fileName.get(position)));
                        dialog.create().dismiss();
                        break;
                }
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    private void setFileData(File lastFile) {
        fileName.clear();
        if (!lastFile.equals(Environment.getExternalStorageDirectory()))
            fileName.add("..");
        for (File file : lastFile.listFiles()) {
            String name = file.getName();
            if (!file.isDirectory() || ".".equals(name.substring(0, 1)) || name.isEmpty())
                continue;
            fileName.add(name);
        }
        Logger.d("目录名：" + fileName);
    }

    private class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.MyViewHolder> {

        private List<String> mFileName;
        private OnClickListener mListener;

        public DialogAdapter(List<String> fileName) {
            this.mFileName = fileName;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(View.inflate(parent.getContext(), R.layout.item_file_dialog, null));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.textView.setText(fileName.get(position));
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.clickListener(position, R.id.layout);
                }
            });
            holder.imageChoose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.clickListener(position, R.id.image_choose);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mFileName.size();
        }

        public void setOnClickListener(OnClickListener listener) {
            this.mListener = listener;
        }

        public void setDataList(List<String> fileName) {
            this.mFileName = fileName;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            private View layout;
            private TextView textView;
            private ImageView imageChoose;

            public MyViewHolder(View inflate) {
                super(inflate);
                layout = inflate.findViewById(R.id.layout);
                textView = inflate.findViewById(R.id.text_view);
                imageChoose = inflate.findViewById(R.id.image_choose);
            }

        }

    }

    interface OnClickListener {

        void clickListener(int position, int resId);
    }

    private class MyAdapter extends  RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private List<Music> mMusicData;
        private BaseAdapter.OnItemClickListener mOnItemClickListener;

        private int ITEM_TYPE_DIR = 0;
        private int ITEM_TYPE_MUSIC = 1;

        public MyAdapter() {
            mMusicData = new ArrayList<>();
        }

        public void setMusicData(List<Music> musicData) {
            mMusicData = musicData;
        }

        public void setOnItemClickListener(BaseAdapter.OnItemClickListener listener) {
            this.mOnItemClickListener = listener;
        }

        @Override
        public int getItemViewType(int position) {
            return mMusicData.get(position).isMusic() ? ITEM_TYPE_MUSIC : ITEM_TYPE_DIR;
        }

        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            int layoutId = (viewType == ITEM_TYPE_DIR) ? R.layout.item_music_dir : R.layout.item_music_file;
            return new MyViewHolder(View.inflate(parent.getContext(), layoutId, null));
        }

        @Override
        public void onBindViewHolder(MyAdapter.MyViewHolder holder, final int position) {
            Music music = mMusicData.get(position);
            if (music.isMusic()) {
                holder.textView.setText(music.getTitle());
                holder.textCount.setText(music.getArtist());
                holder.textTime.setText(music.getDuration());
            } else {
                holder.textView.setText(music.getName());
                holder.textCount.setText(music.getChildCount() + "首");
            }
            if (mOnItemClickListener != null) {
                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemClickListener.onItemClickListener(position);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mMusicData.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            private View linearLayout;
            private TextView textView;
            private TextView textCount;
            private TextView textTime;

            public MyViewHolder(View inflate) {
                super(inflate);
                linearLayout = inflate.findViewById(R.id.linear_layout);
                textView = inflate.findViewById(text_title);
                textCount = inflate.findViewById(R.id.text_count);
                textTime = inflate.findViewById(R.id.text_time);
            }

        }

    }

}
