package com.itstrongs.myapp.fragment.photo;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.itstrongs.library.base.BaseAdapter;
import com.itstrongs.library.base.BaseFragment;
import com.itstrongs.library.helper.Logger;
import com.itstrongs.myapp.R;
import com.itstrongs.myapp.data.ConstantPool;
import com.itstrongs.myapp.activity.DetailsActivity;
import com.itstrongs.myapp.activity.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by itstrongs on 2017/11/7.
 */
public class QiFragment extends BaseFragment<MainActivity> {

    @BindView(R.id.recycle_view)
    RecyclerView recycleView;

    private MyAdapter mGirlAdapter;
    private ArrayList<String> mStrings;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_qi;
    }

    @Override
    protected void initView() {
        final StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(mGirlAdapter = new MyAdapter());
        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
            }
        });
        mStrings = loadQiImageData();
        mGirlAdapter.addDataList(mStrings);
        mGirlAdapter.notifyDataSetChanged();
    }

    public ArrayList<String> loadQiImageData() {
        ArrayList<String> imageUrlList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            imageUrlList.add(ConstantPool.URL_QI + i + ".jpg");
        }
        Logger.d("mImageUrlList:" + imageUrlList.toString());
        return imageUrlList;
    }

    class MyAdapter extends BaseAdapter<MyAdapter.MyViewHolder, String> {

        @Override
        protected RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {
            return new MyViewHolder(View.inflate(parent.getContext(), R.layout.item_list_girl, null));
        }

        @Override
        protected void bindViewHolder(MyViewHolder holder, List<String> dataList, int position) {
            Picasso.with(mContext).load(dataList.get(position)).into(holder.imageView);
            setOnItemClickListener(holder.imageView, new OnItemClickListener() {
                @Override
                public void onItemClickListener(int position) {
                    Intent intent = new Intent(mContext, DetailsActivity.class);
                    intent.putExtra("position", position);
                    intent.putStringArrayListExtra("image_urls", mStrings);
                    startActivity(intent);
                }
            });
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
