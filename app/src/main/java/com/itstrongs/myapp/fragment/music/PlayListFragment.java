package com.itstrongs.myapp.fragment.music;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itstrongs.library.base.BaseAdapter;
import com.itstrongs.library.base.BaseFragment;
import com.itstrongs.library.base.BasePresenter;
import com.itstrongs.library.helper.Logger;
import com.itstrongs.myapp.R;
import com.itstrongs.myapp.data.MusicHelper;
import com.itstrongs.myapp.data.bean.Music;

import java.util.List;

import butterknife.BindView;

/**
 * Created by itstrongs on 2017/12/1.
 */
public class PlayListFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MyAdapter mMyAdapter;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_play_list;
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(mMyAdapter = new MyAdapter());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        List<Music> playList = MusicHelper.getInstance().getPlayList();
        Logger.d("播放列表：" + playList);
        if (playList != null && mMyAdapter != null) {
            mMyAdapter.setDataList(playList);
            mMyAdapter.notifyDataSetChanged();
        }
    }

    class MyAdapter extends BaseAdapter<MyAdapter.MyViewHolder, Music> {
        @Override
        protected RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {
            return new MyViewHolder(View.inflate(parent.getContext(), R.layout.item_music_file, null));
        }

        @Override
        protected void bindViewHolder(MyViewHolder holder, List<Music> dataList, int position) {
            Music music = dataList.get(position);
            holder.textTitle.setText(music.getTitle() != null ? music.getTitle() : music.getName());
            holder.textCount.setText(music.getArtist() != null ? music.getArtist() : "未知");
            holder.textTime.setText(music.getDuration());
            setOnItemClickListener(holder.linearLayout, new BaseAdapter.OnItemClickListener() {
                @Override
                public void onItemClickListener(int position) {
                    //TODO 播放音乐
                }
            });
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            private View linearLayout;
            private TextView textTitle;
            private TextView textCount;
            private TextView textTime;

            public MyViewHolder(View inflate) {
                super(inflate);
                linearLayout = inflate.findViewById(R.id.linear_layout);
                textTitle = inflate.findViewById(R.id.text_title);
                textCount = inflate.findViewById(R.id.text_count);
                textTime = inflate.findViewById(R.id.text_time);
            }

        }
    }

}
