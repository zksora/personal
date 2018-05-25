package com.itstrongs.myapp.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.itstrongs.library.helper.SPHelper;
import com.itstrongs.myapp.R;
import com.itstrongs.myapp.data.ConstantPool;
import com.itstrongs.myapp.view.ScaleImageView;
import com.itstrongs.myapp.view.gallery.AnimManager;
import com.itstrongs.myapp.view.gallery.BlurBitmapUtil;
import com.itstrongs.myapp.view.gallery.GalleryRecyclerView;
import com.itstrongs.myapp.view.gallery.RecyclerAdapter;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by itstrongs on 2017/12/7.
 */
public class DetailsActivity extends AppCompatActivity {

    private Context mContext;

    private int mPosition;
    private ViewPager mViewPager;
    private ArrayList<String> mImageUrls;

    private GalleryRecyclerView mRecyclerView;
    private RelativeLayout mContainer;

    private Map<String, Drawable> mTSDraCacheMap = new HashMap<>();
    private static final String KEY_PRE_DRAW = "key_pre_draw";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mPosition = getIntent().getIntExtra("position", 0);
        mImageUrls = getIntent().getStringArrayListExtra("image_urls");
        boolean isFull = SPHelper.getBoolean(mContext, ConstantPool.SP_IS_FULL_IMAGE, true);
        if (isFull) {
            setTheme(R.style.TranslucentTheme);
            setContentView(R.layout.activity_details0);
            initView0();
        } else {
            setTheme(SPHelper.getInt(this, ConstantPool.SP_THEME_ID, R.style.CustomThemeBlack));
            setContentView(R.layout.activity_details1);
            initView1();
        }
    }

    private void initView0() {
        mRecyclerView = (GalleryRecyclerView) findViewById(R.id.rv_list);
        mContainer = (RelativeLayout) findViewById(R.id.rl_container);

        final RecyclerAdapter adapter = new RecyclerAdapter(getApplicationContext(), mImageUrls);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.initFlingSpeed(5000)                                   // 设置滑动速度（像素/s）
                .initPageParams(0, 60)     // 设置页边距和左右图片的可见宽度，单位dp
                .setAnimFactor(0.15f)                                   // 设置切换动画的参数因子
                .setAnimType(AnimManager.ANIM_BOTTOM_TO_TOP);            // 设置切换动画类型，目前有AnimManager.ANIM_BOTTOM_TO_TOP和目前有AnimManager.ANIM_TOP_TO_BOTTOM


        // 背景高斯模糊 & 淡入淡出
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    setBlurImage();
                }
            }
        });
        setBlurImage();
    }

    private void initView1() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("girl");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<View> imageViews = new ArrayList<>();
        for (String url : mImageUrls) {
            View view = View.inflate(this, R.layout.item_details, null);
            ScaleImageView imageView = view.findViewById(R.id.image_view);
            Picasso.with(this).load(url).into(imageView);
            imageViews.add(view);
        }
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(new DetailsAdapter(imageViews));
        mViewPager.setCurrentItem(mPosition);
    }

    /**
     * 设置背景高斯模糊
     */
    public void setBlurImage() {
        RecyclerAdapter adapter = (RecyclerAdapter) mRecyclerView.getAdapter();

        if (adapter == null || mRecyclerView == null) {
            return;
        }
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                // 获取当前位置的图片资源ID
                String resourceUrl = ((RecyclerAdapter) mRecyclerView.getAdapter()).getResId(mRecyclerView.getScrolledPosition());
                // 将该资源图片转为Bitmap
                Picasso.with(mContext).load(resourceUrl).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                        Bitmap resBmp = bitmap;
                        Bitmap resBlurBmp = BlurBitmapUtil.blurBitmap(mRecyclerView.getContext(), resBmp, 10f);
                        Drawable resBlurDrawable = new BitmapDrawable(resBlurBmp);
                        // 获取前一页的Drawable
                        Drawable preBlurDrawable = mTSDraCacheMap.get(KEY_PRE_DRAW) == null ? resBlurDrawable : mTSDraCacheMap.get(KEY_PRE_DRAW);

                        /* 以下为淡入淡出效果 */
                        Drawable[] drawableArr = {preBlurDrawable, resBlurDrawable};
                        TransitionDrawable transitionDrawable = new TransitionDrawable(drawableArr);
                        mContainer.setBackgroundDrawable(transitionDrawable);
                        transitionDrawable.startTransition(500);

                        // 存入到cache中
                        mTSDraCacheMap.put(KEY_PRE_DRAW, resBlurDrawable);
                    }

                    @Override
                    public void onBitmapFailed(Drawable drawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable drawable) {

                    }
                });
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public class DetailsAdapter extends PagerAdapter {

        private ArrayList<View> mGirls;

        public DetailsAdapter(ArrayList<View> girls) {
            mGirls = girls;
        }

        @Override
        public int getCount() {
            return mGirls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mGirls.get(position));
            return mGirls.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

}
