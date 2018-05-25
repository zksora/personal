package com.itstrongs.library.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Recycler view base adapter.
 *
 * Created by itstrongs on 2017/12/1.
 */
public abstract class BaseAdapter<T extends RecyclerView.ViewHolder, K> extends RecyclerView.Adapter {
    
    private List<K> mDataList;
    private OnItemClickListener mOnItemClickListener;
    private View mView;

    public BaseAdapter() {
        mDataList = new ArrayList();
    }

    public void setOnItemClickListener(View view, OnItemClickListener listener) {
        this.mView = view;
        this.mOnItemClickListener = listener;
    }
    
    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        return (T) createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        bindViewHolder((T) holder, mDataList, position);
        if (mOnItemClickListener != null) {
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClickListener(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setDataList(List<K> dataList) {
        this.mDataList = dataList;
    }

    public void addDataList(List<K> dataList) {
        this.mDataList.addAll(dataList);
    }

    protected abstract RecyclerView.ViewHolder createViewHolder(ViewGroup parent);

    protected abstract void bindViewHolder(T holder, List<K> dataList, int position);

    public interface OnItemClickListener {

        void onItemClickListener(int position);
    }
}
