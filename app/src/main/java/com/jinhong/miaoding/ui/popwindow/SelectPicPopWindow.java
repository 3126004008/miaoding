package com.jinhong.miaoding.ui.popwindow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.jinhong.miaoding.R;
import com.jinhong.miaoding.ui.activity.PublishEmojiActivity;

/**
 * Created by chrc on 2018/11/6.
 */

public class SelectPicPopWindow extends PopupWindow implements View.OnClickListener {

    private Context mContext;
    private View.OnClickListener onClickListener;

    public SelectPicPopWindow(Context context, View view, View.OnClickListener onClickListener) {
        super(context);
        this.onClickListener = onClickListener;
        init(context, view);
    }

    private void init(Context context, View parent) {
        mContext= context;

        View view = LayoutInflater.from(context).inflate(R.layout.pop_select_pic, null, false);
        view.findViewById(R.id.tv_take_pic).setOnClickListener(this);
        view.findViewById(R.id.tv_choose_pic).setOnClickListener(this);

        setContentView(view);
        //设置SelectPicPopupWindow弹出窗体可点击
        setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        //进入退出的动画
        setAnimationStyle(R.style.publish_popwindow_anim_style);
        //点击外部消失
        setOutsideTouchable(true);
        //设置可以点击
        setTouchable(true);
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        setBackgroundDrawable(new BitmapDrawable());
        // 注：此处的R.id.main则是最外层布局View
        showAtLocation(parent, Gravity.CENTER, 0, 0);
    }

    @Override
    public void onClick(View view) {
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
        dismiss();
    }

}
