package com.jinhong.miaoding.listener;

import android.view.View;

import com.jinhong.miaoding.event.FilterEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by chrc on 2018/11/8.
 */

public class FilterItemListener implements View.OnClickListener {

    private int type;
    private int pos;

    public FilterItemListener(int type, int pos) {
        this.type = type;
        this.pos = pos;
    }

    @Override
    public void onClick(View v) {
        EventBus.getDefault().post(new FilterEvent(type, pos));
    }
}
