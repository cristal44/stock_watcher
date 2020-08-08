package com.example.mywatchlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class MyViewPager extends ViewPager {

    private boolean enableSwipe;

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.enableSwipe  = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return enableSwipe ? super.onTouchEvent(ev) : false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return enableSwipe ? super.onInterceptTouchEvent(ev) : false;
    }

    public void setPagingEnabled(boolean enableSwipe){
        this.enableSwipe = enableSwipe;
    }
}
