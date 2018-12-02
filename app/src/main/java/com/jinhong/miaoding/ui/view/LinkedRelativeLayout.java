package com.jinhong.miaoding.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by chrc on 2018/11/26.
 */

public class LinkedRelativeLayout extends RelativeLayout {

    private LinkViewPager mFollowViewPager;

    public LinkedRelativeLayout(Context context) {
        super(context);
    }

    public LinkedRelativeLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setFollowViewPager(LinkViewPager page) {
        mFollowViewPager = page;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getY() >= mFollowViewPager.getTop() && ev.getY() <= mFollowViewPager.getBottom()) {
            return super.onInterceptTouchEvent(ev);
        }
        return mFollowViewPager.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getY() >= mFollowViewPager.getTop() && ev.getY() <= mFollowViewPager.getBottom()) {
            return super.onInterceptTouchEvent(ev);
        }
        return mFollowViewPager.onTouchEvent(ev);
    }

}
