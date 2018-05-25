package com.itstrongs.demo;

import android.widget.TextView;

/**
 * Created by itstrongs on 2018/1/3.
 */
class PayActivity extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initView() {
        TextView textView = (TextView) mActivity.findViewById(R.id.text_view);
        textView.setText("支付2");
    }
}