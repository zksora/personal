package com.itstrongs.myapp.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import com.itstrongs.library.helper.SPHelper;
import com.itstrongs.myapp.R;
import com.itstrongs.myapp.data.ConstantPool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by itstrongs on 2017/12/7.
 */
public class SettingActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.switch_view_0)
    SwitchCompat switchView0;
    @BindView(R.id.switch_view_1)
    SwitchCompat switchView1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Context mContext;
    private int mCurrentTheme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPHelper.getInt(this, ConstantPool.SP_THEME_ID, R.style.CustomThemeBlack));
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        mContext = this;
        initView();
    }

    private void initView() {
        switchView0.setChecked(SPHelper.getInt(this, ConstantPool.SP_THEME_ID,
                R.style.CustomThemeBlack) == R.style.CustomThemeNight);
        switchView1.setChecked(SPHelper.getBoolean(this, ConstantPool.SP_IS_FULL_IMAGE, true));
        switchView0.setOnCheckedChangeListener(this);
        switchView1.setOnCheckedChangeListener(this);
        toolbar.setTitle(R.string.action_settings);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCurrentTheme = SPHelper.getInt(this, ConstantPool.SP_THEME_ID);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mCurrentTheme != SPHelper.getInt(this, ConstantPool.SP_THEME_ID)) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        super.onBackPressed();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        switch (compoundButton.getId()) {
            case R.id.switch_view_0:
                if (isChecked) {
                    int lastTheme = SPHelper.getInt(mContext, ConstantPool.SP_THEME_ID, R.style.CustomThemeBlack);
                    SPHelper.putInt(mContext, ConstantPool.SP_LAST_THEME_ID, lastTheme);
                    SPHelper.putInt(mContext, ConstantPool.SP_THEME_ID, R.style.CustomThemeNight);
                } else {
                    SPHelper.putInt(mContext, ConstantPool.SP_THEME_ID, SPHelper.getInt(mContext, ConstantPool.SP_LAST_THEME_ID));
                }
                break;
            case R.id.switch_view_1:
                SPHelper.putBoolean(mContext, ConstantPool.SP_IS_FULL_IMAGE, isChecked);
                break;
        }
    }

    @OnClick({R.id.setting_menu_0, R.id.setting_menu_1, R.id.setting_menu_2, R.id.setting_menu_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_menu_0:
                showListDialog();
                break;
            case R.id.setting_menu_1:
                break;
            case R.id.setting_menu_2:
                break;
            case R.id.setting_menu_3:
                startActivity(new Intent(mContext, AboutActivity.class));
                break;
        }
    }

    private void showListDialog() {
        final String[] items = {"黑色", "黄色", "紫色", "蓝色", "红色"};
        AlertDialog.Builder listDialog = new AlertDialog.Builder(mContext);
        listDialog.setTitle("选择主题颜色");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        SPHelper.putInt(mContext, ConstantPool.SP_THEME_ID, R.style.CustomThemeBlack);
                        break;
                    case 1:
                        SPHelper.putInt(mContext, ConstantPool.SP_THEME_ID, R.style.CustomThemeYellow);
                        break;
                    case 2:
                        SPHelper.putInt(mContext, ConstantPool.SP_THEME_ID, R.style.CustomThemeViolet);
                        break;
                    case 3:
                        SPHelper.putInt(mContext, ConstantPool.SP_THEME_ID, R.style.CustomThemeBlue);
                        break;
                    case 4:
                        SPHelper.putInt(mContext, ConstantPool.SP_THEME_ID, R.style.CustomThemeRed);
                        break;
                }
                recreate();
            }
        });
        listDialog.show();
    }
}
