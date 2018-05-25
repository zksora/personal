package com.itstrongs.myapp.view.gallery;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * 用RecyclerView做一个小清新的Gallery效果
 * @https://juejin.im/post/5a30fe5a6fb9a045132ab1bf
 *
 * Created by RyanLee on 2017/12/8.
 */
public class GalleryRecyclerView extends RecyclerView {

    private int FLING_SPEED = 1000; // 滑动速度

    private ScrollManager mScrollManager;
    private GalleryItemDecoration mDecoration;

    public GalleryRecyclerView(Context context) {
        this(context, null);
    }

    public GalleryRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GalleryRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        attachDecoration();
        attachToRecyclerHelper();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);

        if (getAdapter().getItemCount() <= 0) {
            return;
        }
        // 获得焦点后滑动至第0项，避免第0项的margin不对
        smoothScrollToPosition(0);
    }

    private void attachDecoration() {
        mDecoration = new GalleryItemDecoration();
        addItemDecoration(mDecoration);
    }


    @Override
    public boolean fling(int velocityX, int velocityY) {
        velocityX = balancelocity(velocityX);
        velocityY = balancelocity(velocityY);
        return super.fling(velocityX, velocityY);
    }

    /**
     * 返回滑动速度值
     *
     * @param velocity
     * @return
     */
    private int balancelocity(int velocity) {
        if (velocity > 0) {
            return Math.min(velocity, FLING_SPEED);
        } else {
            return Math.max(velocity, -FLING_SPEED);
        }
    }

    /**
     * 连接RecyclerHelper
     */
    private void attachToRecyclerHelper() {
        mScrollManager = new ScrollManager(this);
        mScrollManager.initSnapHelper();
        mScrollManager.initScrollListener();
    }

    /**
     * 设置页面参数，单位dp
     *
     * @param pageMargin           默认：0dp
     * @param leftPageVisibleWidth 默认：50dp
     * @return
     */
    public GalleryRecyclerView initPageParams(int pageMargin, int leftPageVisibleWidth) {
        GalleryItemDecoration.mPageMargin = pageMargin;
        GalleryItemDecoration.mLeftPageVisibleWidth = leftPageVisibleWidth;
        return this;
    }

    /**
     * 设置滑动速度（像素/s）
     *
     * @param speed
     * @return
     */
    public GalleryRecyclerView initFlingSpeed(int speed) {
        this.FLING_SPEED = speed;
        return this;
    }

    /**
     * 设置动画因子
     *
     * @param factor
     * @return
     */
    public GalleryRecyclerView setAnimFactor(float factor) {
        AnimManager.getInstance().setmAnimFactor(factor);
        return this;
    }

    /**
     * 设置动画类型
     *
     * @param type
     * @return
     */
    public GalleryRecyclerView setAnimType(int type) {
        AnimManager.getInstance().setmAnimType(type);
        return this;
    }

    /**
     * 设置点击事件
     * @param mListener
     */
    public GalleryRecyclerView setOnItemClickListener(OnItemClickListener mListener) {
        if (mDecoration != null) {
            mDecoration.setOnItemClickListener(mListener);
        }
        return this;
    }

    public int getOrientation() {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getLayoutManager();
        if (linearLayoutManager == null) {
            try {
                throw new Exception("请设置LayoutManager为LinearLayoutManager");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return linearLayoutManager.getOrientation();
        }
        return 0;
    }

    public LinearLayoutManager getLinearLayoutManager() {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getLayoutManager();
        if (linearLayoutManager == null) {
            try {
                throw new Exception("请设置LayoutManager为LinearLayoutManager");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return linearLayoutManager;
        }
        return null;
    }

    public int getScrolledPosition() {
        if (mScrollManager == null) {
            return 0;
        } else {
            return mScrollManager.getPosition();
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
