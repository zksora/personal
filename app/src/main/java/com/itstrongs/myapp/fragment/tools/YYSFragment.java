package com.itstrongs.myapp.fragment.tools;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.itstrongs.library.base.BaseFragment;
import com.itstrongs.library.base.BasePresenter;
import com.itstrongs.myapp.R;
import com.itstrongs.myapp.service.YYSService;
import com.itstrongs.myapp.activity.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by itstrongs on 2017/11/25.
 */
public class YYSFragment extends BaseFragment<MainActivity> {

    @BindView(R.id.btn_0)
    Button btn0;
    @BindView(R.id.btn_1)
    Button btn1;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_yys;
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected void initView() { }

    @OnClick({R.id.btn_0, R.id.btn_1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_0:
                mActivity.startService(new Intent(mActivity, YYSService.class));
                break;
            case R.id.btn_1:
                mActivity.stopService(new Intent(mActivity, YYSService.class));
                break;
        }
    }

    @Override
    public void onDestroy() {
        mActivity.stopService(new Intent(mActivity, YYSService.class));
        super.onDestroy();
    }

}