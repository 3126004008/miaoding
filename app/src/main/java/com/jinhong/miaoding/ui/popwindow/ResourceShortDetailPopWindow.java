package com.jinhong.miaoding.ui.popwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.jinhong.miaoding.R;

/**
 * Created by chrc on 2018/11/8.
 */

public class ResourceShortDetailPopWindow extends PopupWindow {

    private Context mContext;

    public ResourceShortDetailPopWindow(Context context, View view) {
        super(context);
        init(context, view);
    }

    private void init(Context context, View parent) {
        mContext= context;

        View view = LayoutInflater.from(context).inflate(R.layout.pop_select_pic, null, false);

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
}
