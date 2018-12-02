package com.jinhong.miaoding.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jinhong.miaoding.R;
import com.jinhong.miaoding.listener.FilterItemListener;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by chrc on 2018/11/8.
 */

public class FilterView extends LinearLayout {

    public static final int EXPLORE = 0;
    public static final int NEARBY_PERSON = 1;
    public static final int NEARBY_THING = 2;
    public static final int TIME = 3;

    TextView filterTitleTv;
    LinearLayout filterContainerLL;

    Context context;
    String title;

    public FilterView(Context context) {
        super(context);
        init(context);
    }

    public FilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.filter_view);
        title = array.getString(R.styleable.filter_view_filter_title);
        array.recycle();

        init(context);
    }

    private void init(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.view_filter_layout, this);
        filterTitleTv = findViewById(R.id.tv_filter_title);
        filterContainerLL = findViewById(R.id.ll_filter_items);

        filterTitleTv.setText(title);
    }

    public void setData(String[] items, int type) {
        if (items != null) {
            for (int i = 0; i < items.length; i++) {
                TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.item_filter_view, null);
                textView.setText(items[i]);
                textView.setOnClickListener(new FilterItemListener(type, i));
                filterContainerLL.addView(textView);
            }
        }
    }

    /*@IntDef({EXPLORE, NEARBY_PERSON, NEARBY_THING, TIME})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FilterType {
    }*/

}
