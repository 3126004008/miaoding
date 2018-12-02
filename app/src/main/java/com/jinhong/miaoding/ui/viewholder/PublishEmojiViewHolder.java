package com.jinhong.miaoding.ui.viewholder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jinhong.miaoding.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chrc on 2018/11/6.
 */

public class PublishEmojiViewHolder extends RecyclerView.ViewHolder {

    SimpleDraweeView simpleDraweeView;
    TextView textView;

    View preView;

    public PublishEmojiViewHolder(@NonNull View itemView) {
        super(itemView);
        simpleDraweeView = itemView.findViewById(R.id.simple_drawee);
        textView = itemView.findViewById(R.id.indicator);
    }

    public void setData(Integer i) {
        textView.setText(String.valueOf(i));
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setSelected(true);
                if (preView != null) {
                    preView.setSelected(false);
                }
                preView = textView;
            }
        });
    }
}
