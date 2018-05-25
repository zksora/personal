package com.itstrongs.myapp.fragment.plan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.itstrongs.library.base.BaseFragment;
import com.itstrongs.library.helper.Logger;
import com.itstrongs.myapp.R;
import com.itstrongs.myapp.data.bean.Plan;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by itstrongs on 2017/12/8.
 */
public class PlanTab0Fragment extends BaseFragment {

    @BindView(R.id.text_weplan_title)
    TextView textWeplanTitle;
    @BindView(R.id.list_view_weplan)
    ExpandableListView mListView;
    @BindView(R.id.edit_weplan_add)
    EditText mEditAdd;
    @BindView(R.id.layout_weplan_add)
    LinearLayout mLayoutAdd;

    private TextView mTextTitle;
    private PopupWindow mPopupWindow;

    private String mParentId;
    private List<Plan> mParentPlanList;
    private MyExpandableListAdapter mAdapter;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_plan_tab_0;
    }

    @Override
    protected void initView() {
        mParentPlanList = new ArrayList<>();
        mListView.setAdapter(mAdapter = new MyExpandableListAdapter());
        mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Logger.d("groupPosition:" + groupPosition);
                return false;
            }
        });
        mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Logger.d("onGroupClick：" + childPosition);
                return true;
            }
        });
    }

    @OnClick({R.id.img_weplan_add, R.id.text_weplan_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_weplan_add:
                break;
            case R.id.text_weplan_confirm:
                break;
        }
    }

    private class MyExpandableListAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return mParentPlanList.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return mParentPlanList.get(groupPosition).getChildPlan().size();
        }

        @Override
        public Plan getGroup(int groupPosition) {
            return mParentPlanList.get(groupPosition);
        }

        @Override
        public Plan getChild(int groupPosition, int childPosition) {
            return mParentPlanList.get(groupPosition).getChildPlan().get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupViewHolder groupViewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_plan_list, parent, false);
                groupViewHolder = new GroupViewHolder();
                groupViewHolder.textTitle = (TextView) convertView.findViewById(R.id.text_item_list_title);
                groupViewHolder.viewAdd = convertView.findViewById(R.id.img_weplan_add);
                convertView.setTag(groupViewHolder);
            } else {
                groupViewHolder = (GroupViewHolder) convertView.getTag();
            }
            final Plan planBean = mParentPlanList.get(groupPosition);
            groupViewHolder.textTitle.setText(planBean.getTitle());
            groupViewHolder.viewAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mLayoutAdd.setVisibility(View.VISIBLE);
                    mParentId = planBean.getId();
                }
            });
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildViewHolder childViewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_plan_list_child, parent, false);
                childViewHolder = new ChildViewHolder();
                childViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.text_item_list_title);
                convertView.setTag(childViewHolder);
            } else {
                childViewHolder = (ChildViewHolder) convertView.getTag();
            }
            Plan planBean = mParentPlanList.get(groupPosition).getChildPlan().get(childPosition);
            childViewHolder.tvTitle.setText(planBean.getTitle());
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        class GroupViewHolder {
            TextView textTitle;
            View viewAdd;
        }

        class ChildViewHolder {
            TextView tvTitle;
        }
    }
}
