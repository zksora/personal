package com.itstrongs.myapp.fragment.demo;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.itstrongs.library.base.BaseFragment;
import com.itstrongs.myapp.R;
import com.itstrongs.myapp.data.JsoupHelper;
import com.itstrongs.myapp.data.bean.Joke;
import com.itstrongs.myapp.activity.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by itstrongs on 2017/12/11.
 */
public class DuanziFragment extends BaseFragment<MainActivity> implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private int mHtmlPage;
    private List<Joke> mJokeList;
    private MyAdapter mMyAdapter;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(mMyAdapter = new MyAdapter());
        swipeRefresh.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        mHtmlPage = 1;
        mJokeList = new ArrayList<>();
        loadData();
    }

    @Override
    public void onRefresh() {
        Random random = new Random();
        int page;
        do {
            page = random.nextInt(10);
        } while (mHtmlPage == page);
        mHtmlPage = page;
        loadData();
    }

    private void loadData() {
        JsoupHelper.getInstance().doReptileDuanzi(mHtmlPage, new Consumer<List<Joke>>() {
            @Override
            public void accept(List<Joke> jokes) throws Exception {
                mMyAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(View.inflate(parent.getContext(), R.layout.item_joke, null));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Joke joke = mJokeList.get(position);
            if (joke.getImageUrl().isEmpty()) {
                holder.imageView.setVisibility(View.GONE);
            } else {
                Picasso.with(mContext).load(joke.getImageUrl()).into(holder.imageView);
            }
            holder.textView.setText(joke.getContent());
        }

        @Override
        public int getItemCount() {
            return mJokeList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView textView;

            public MyViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.image_view);
                textView = itemView.findViewById(R.id.text_view);
            }
        }
    }
}
