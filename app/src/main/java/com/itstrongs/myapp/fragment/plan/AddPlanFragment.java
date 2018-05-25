package com.itstrongs.myapp.fragment.plan;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.itstrongs.library.helper.AppHelper;
import com.itstrongs.library.utils.ToastUtils;
import com.itstrongs.myapp.R;
import com.itstrongs.myapp.data.bean.Plan;

/**
 * Created by itstrongs on 2017/9/15.
 */
public class AddPlanFragment extends Fragment implements View.OnClickListener {

    private Activity mActivity;
    private EditText mEditTitle;
    private EditText mEditContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_plan_add, container, false);
        mActivity = getActivity();
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        inflate.findViewById(R.id.img_add_cancel).setOnClickListener(this);
        inflate.findViewById(R.id.img_add_add).setOnClickListener(this);
        mEditTitle = (EditText) inflate.findViewById(R.id.edit_add_plan_title);
        mEditContent = (EditText) inflate.findViewById(R.id.edit_add_plan_content);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_add_cancel:
                mActivity.onBackPressed();
                break;
            case R.id.img_add_add:
                doAddPlan();
                break;
        }
    }

    private void doAddPlan() {
        String planTitle = mEditTitle.getText().toString();
        if (planTitle.isEmpty()) {
            ToastUtils.show(mActivity, "标题不能为空");
        } else {
            Plan plan = new Plan();
            plan.setTitle(planTitle);
            plan.setContent(mEditContent.getText().toString());
            plan.setCreateDate(AppHelper.getCurrentTime());
            PlanDBHandler.getInstance(mActivity).doAddPlan(plan);
            mActivity.onBackPressed();
        }
    }

}
