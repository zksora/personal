package com.itstrongs.myapp.fragment.photo.girl;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.itstrongs.library.base.BaseAdapter;
import com.itstrongs.library.base.BaseFragment;
import com.itstrongs.library.base.BasePresenter;
import com.itstrongs.library.helper.Logger;
import com.itstrongs.library.utils.ActivityUtils;
import com.itstrongs.myapp.R;
import com.itstrongs.myapp.data.bean.Girl;
import com.itstrongs.myapp.activity.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by itstrongs on 2017/11/3.
 */
public class GirlFragment extends BaseFragment implements GirlContract.View {

    @BindView(R.id.recycle_view)
    RecyclerView recycleView;

    private GirlContract.Presenter mPresenter;
    private MyAdapter mGirlAdapter;
    private boolean mIsLoading;

    @Override
    public void setPresenter(GirlContract.Presenter presenter) {
        mPresenter = ActivityUtils.checkNotNull(presenter);
    }

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_girl;
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return new GirlPresenter(this);
    }

    @Override
    protected void initView() {
        final StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(mGirlAdapter = new MyAdapter(getContext()));
        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
                if (!recycleView.canScrollVertically(1) && !mIsLoading) {
                    Logger.d("底部上拉加载更多");
                    mIsLoading = true;
                    mPresenter.doLoadMoreData();
                }
            }
        });
        mGirlAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                showGirlDetails(position);
            }
        });
    }

    @Override
    public void showImage(Girl girlBean) {
        mGirlAdapter.addGirlData(girlBean.getResults());
        mGirlAdapter.notifyDataSetChanged();
        mIsLoading = false;
    }

    @Override
    public void showGirlDetails(int position) {
        ArrayList<String> imageUrls = new ArrayList<>();
        for (Girl.ResultsBean bean : mPresenter.doGetGirlData()) {
            imageUrls.add(bean.getUrl());
        }
        Intent intent = new Intent(mContext, DetailsActivity.class);
        intent.putExtra("position", position);
        intent.putStringArrayListExtra("image_urls", imageUrls);
        startActivity(intent);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private Context mContext;
        private List<Girl.ResultsBean> mResultsBean;
        private BaseAdapter.OnItemClickListener mOnItemClickListener;

        public MyAdapter(Context context) {
            this.mContext = context;
            mResultsBean = new ArrayList<>();
        }

        public void addGirlData(List<Girl.ResultsBean> resultsBean) {
            this.mResultsBean.addAll(resultsBean);
        }

        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(View.inflate(parent.getContext(), R.layout.item_list_girl, null));
        }

        @Override
        public void onBindViewHolder(MyAdapter.MyViewHolder holder, final int position) {
            MyViewHolder girlHolder = holder;
            Picasso.with(mContext).load(mResultsBean.get(position).getUrl()).into(girlHolder.imageView);
            if (mOnItemClickListener != null) {
                girlHolder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemClickListener.onItemClickListener(position);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mResultsBean.size();
        }

        public void setOnItemClickListener(BaseAdapter.OnItemClickListener listener) {
            this.mOnItemClickListener = listener;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;

            public MyViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.image_view);
            }
        }
    }
}
