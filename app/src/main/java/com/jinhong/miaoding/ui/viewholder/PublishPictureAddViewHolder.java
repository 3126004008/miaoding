package com.jinhong.miaoding.ui.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jinhong.miaoding.R;
import com.jinhong.miaoding.data.PublishPicData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chrc on 2018/11/6.
 */

public class PublishPictureAddViewHolder extends RecyclerView.ViewHolder {

    SimpleDraweeView simpleDraweeView;

    public PublishPictureAddViewHolder(@NonNull View itemView) {
        super(itemView);
        simpleDraweeView = itemView.findViewById(R.id.simple_drawee_publish_pic_add);
    }

    public void setData(View.OnClickListener onClickListener) {
        simpleDraweeView.setOnClickListener(onClickListener);
    }
}
