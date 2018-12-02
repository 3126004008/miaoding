package com.jinhong.miaoding.ui.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jinhong.miaoding.R;
import com.jinhong.miaoding.data.PublishPicData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chrc on 2018/11/6.
 */

public class PublishPictureViewHolder extends RecyclerView.ViewHolder {

    SimpleDraweeView simpleDraweeView;
    ImageView deleteIv;

    public PublishPictureViewHolder(@NonNull View itemView) {
        super(itemView);
        simpleDraweeView = itemView.findViewById(R.id.simple_drawee_publish_pic);
        deleteIv = itemView.findViewById(R.id.iv_delete);
    }

    /**
     *
     * @param data
     */
    public void setData(PublishPicData data, View.OnClickListener onClickListener) {
        simpleDraweeView.setImageURI(data.picPath);
        deleteIv.setOnClickListener(onClickListener);
    }
}
