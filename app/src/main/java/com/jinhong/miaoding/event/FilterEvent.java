package com.jinhong.miaoding.event;

/**
 * Created by chrc on 2018/11/8.
 */

public class FilterEvent {

    private int type;
    private int pos;

    public FilterEvent(int type, int pos) {
        this.type = type;
        this.pos = pos;
    }
}
