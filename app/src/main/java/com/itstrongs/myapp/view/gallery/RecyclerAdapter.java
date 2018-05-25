package com.itstrongs.myapp.view.gallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.itstrongs.myapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by RyanLee on 2017/12/7.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {

    private Context mContext;
    private List<String> mDatas;

    public RecyclerAdapter(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_gallery, parent, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Picasso.with(mContext).load(mDatas.get(position)).into(holder.mView);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        public final ImageView mView;

        public MyHolder(View itemView) {
            super(itemView);
            mView = itemView.findViewById(R.id.iv_photo);
        }

    }

    public String getResId(int position) {
        return mDatas == null ? "" : mDatas.get(position);
    }

}
