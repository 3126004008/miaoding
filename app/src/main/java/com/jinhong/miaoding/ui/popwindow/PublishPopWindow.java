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
import com.jinhong.miaoding.ui.activity.PublishActivity;
import com.jinhong.miaoding.ui.activity.PublishEmojiActivity;

import butterknife.OnClick;

/**
 * Created by chrc on 2018/11/6.
 */

public class PublishPopWindow extends PopupWindow implements View.OnClickListener {

    private Context mContext;
    private View parent;

    public PublishPopWindow(Context context, View view) {
        super(context);
        init(context, view);
    }

    public PublishPopWindow(Context context, AttributeSet attrs, View view) {
        super(context, attrs);
        init(context, view);
    }

    private void init(Context context, View parent) {
        mContext= context;
        this.parent = parent;

        View view = LayoutInflater.from(context).inflate(R.layout.pop_publish, null, false);
        view.findViewById(R.id.tv_talk).setOnClickListener(this);
        view.findViewById(R.id.tv_eat).setOnClickListener(this);
        view.findViewById(R.id.tv_play).setOnClickListener(this);

        setContentView(view);
        setHeight(360);
        setWidth(1080);
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
        showAtLocation(parent, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(mContext, PublishEmojiActivity.class);
        switch (view.getId()) {
            case R.id.tv_talk:
//                mContext.startActivity(intent);
                break;
            case R.id.tv_eat:
                break;
            case R.id.tv_play:
                break;
        }

        mContext.startActivity(intent);
        ((Activity)mContext).overridePendingTransition(R.anim.from_bottom_to_top, 0);
        dismiss();
    }

}
