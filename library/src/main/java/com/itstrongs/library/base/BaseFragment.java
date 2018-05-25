package com.itstrongs.library.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * base fragment
 *
 * Created by itstrongs on 2017/11/3.
 */
public abstract class BaseFragment<T> extends Fragment {

    protected T mActivity;
    protected Context mContext;
    protected Unbinder unbinder;
    protected BasePresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setFragmentLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        mPresenter = onCreatePresenter();
        mActivity = (T) getActivity();
        mContext = getContext();
        initView();
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) mPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected BasePresenter onCreatePresenter() { return null; }

    protected abstract int setFragmentLayout();

    protected abstract void initView();

    protected void initData() { }
}
