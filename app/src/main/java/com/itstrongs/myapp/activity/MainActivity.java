package com.itstrongs.myapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.itstrongs.library.helper.Logger;
import com.itstrongs.library.helper.SPHelper;
import com.itstrongs.myapp.R;
import com.itstrongs.myapp.data.ConstantPool;
import com.itstrongs.myapp.data.PlayerHelper;
import com.itstrongs.myapp.fragment.blog.BlogFragment;
import com.itstrongs.myapp.fragment.demo.DemoFragment;
import com.itstrongs.myapp.fragment.music.MusicListFragment;
import com.itstrongs.myapp.fragment.photo.PhotoFragment;
import com.itstrongs.myapp.fragment.plan.YouthPlanFragment;
import com.itstrongs.myapp.fragment.tools.ToolsFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by itstrongs on 2017/12/12.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.image_view)
    AppCompatImageView imageView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.text_title)
    TextView textTitle;

    private String mCurrentFragment;
    private boolean mIsFirstBackEvent;
    private Map<String, Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPHelper.getInt(this, ConstantPool.SP_THEME_ID, R.style.CustomThemeBlack));
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        initView();
    }

    private void initView() {
        toolbar.setTitle(R.string.drawer_menu_blog);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initFragment() {
        mFragments = new HashMap();
        mFragments.put(BlogFragment.class.getSimpleName(), new BlogFragment());
        mFragments.put(PhotoFragment.class.getSimpleName(), new PhotoFragment());
        mFragments.put(ToolsFragment.class.getSimpleName(), new ToolsFragment());
        mFragments.put(MusicListFragment.class.getSimpleName(), new MusicListFragment());
        mFragments.put(DemoFragment.class.getSimpleName(), new DemoFragment());
        mFragments.put(YouthPlanFragment.class.getSimpleName(), new YouthPlanFragment());
        getSupportFragmentManager().beginTransaction().add(R.id.contentFrame,
                mFragments.get(BlogFragment.class.getSimpleName())).commit();
        mCurrentFragment = BlogFragment.class.getSimpleName();
    }

    public void switchFragment(String tag) {
        Logger.d("当前fragment：" + mCurrentFragment + "，要切换的fragment：" + tag);
        Fragment fragment = mFragments.get(tag);
        if (tag != mCurrentFragment) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (fragment.isAdded()) {
                Logger.d("show " + tag);
                transaction.hide(mFragments.get(mCurrentFragment)).show(fragment).commit();
            } else {
                Logger.d("add " + tag);
                transaction.hide(mFragments.get(mCurrentFragment)).add(R.id.contentFrame, fragment).commit();
            }
            mCurrentFragment = tag;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void setLittleTitle(String title) {
        textTitle.setText(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivityForResult(new Intent(this, SettingActivity.class), 10);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String tag = mCurrentFragment;
        int id = item.getItemId();
        if (id == R.id.nav_blog) {
            tag = BlogFragment.class.getSimpleName();
            toolbarLayout.setTitle(getString(R.string.drawer_menu_blog));
            imageView.setImageResource(R.drawable.ic_bg);
        } else if (id == R.id.nav_photo) {
            tag = PhotoFragment.class.getSimpleName();
            toolbarLayout.setTitle(getString(R.string.drawer_menu_photo));
            imageView.setImageResource(R.drawable.photo_bg);
        } else if (id == R.id.nav_tools) {
            tag = ToolsFragment.class.getSimpleName();
            toolbarLayout.setTitle(getString(R.string.drawer_menu_tools));
            imageView.setImageResource(R.drawable.wallpaper);
        } else if (id == R.id.nav_music) {
            tag = MusicListFragment.class.getSimpleName();
            toolbarLayout.setTitle(getString(R.string.drawer_menu_music));
            imageView.setImageResource(R.drawable.music_bg);
        } else if (id == R.id.nav_demo) {
            tag = DemoFragment.class.getSimpleName();
            toolbarLayout.setTitle(getString(R.string.drawer_menu_demo));
        } else if (id == R.id.nav_plan) {
            tag = YouthPlanFragment.class.getSimpleName();
            toolbarLayout.setTitle(getString(R.string.drawer_menu_plan));
        } else if (id == R.id.nav_setting) {
            startActivityForResult(new Intent(this, SettingActivity.class), 1781);
        }
        switchFragment(tag);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        PlayerHelper.getInstance().saveMusicData(this);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (!mIsFirstBackEvent) {
                mIsFirstBackEvent = true;
                Snackbar.make(toolbar, "再按一次退出应用！", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsFirstBackEvent = false;
                    }
                }, 2000);
            } else {
                super.onBackPressed();
                System.exit(0);
            }
        }
    }
}
