package com.itstrongs.myapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * 自定义左右滑动WebView
 *
 * Created by itstrongs on 2017/12/7.
 */
public class CustomWebView extends WebView {

    private GestureDetector mGestureDetector;

    public CustomWebView(Context context) {
        this(context, null);
    }

    public CustomWebView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float x = e2.getX() - e1.getX();
                float y = e2.getY() - e1.getY();
                if (x > 100) {  // 右滑 事件
                    CustomWebView.this.goBack();
                } else if (x < -100) {  // 左滑事件
                    CustomWebView.this.goForward();
                }
                return true;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

}
