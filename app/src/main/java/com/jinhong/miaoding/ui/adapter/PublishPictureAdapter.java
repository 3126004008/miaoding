package com.jinhong.miaoding.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinhong.miaoding.R;
import com.jinhong.miaoding.data.PublishPicData;
import com.jinhong.miaoding.ui.viewholder.PublishPictureAddViewHolder;
import com.jinhong.miaoding.ui.viewholder.PublishPictureViewHolder;
import com.jinhong.miaoding.utils.StringUtil;

import java.util.List;

/**
 * Created by chrc on 2018/11/6.
 */

public class PublishPictureAdapter extends RecyclerView.Adapter {

    public static final int COMMON_SIZE = 3;
    private static final int NORMAL_TYPE = 1;
    private static final int ADD_TYPE = 0;

    Context context;
    List<PublishPicData> datas;
    PicOperation picOperation;

    public PublishPictureAdapter(Context context, List<PublishPicData> datas, PicOperation picOperation) {
        this.context = context;
        this.datas = datas;
        this.picOperation = picOperation;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == ADD_TYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_publish_pic_add_layout, null);
            PublishPictureAddViewHolder viewHolder = new PublishPictureAddViewHolder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_publish_pic_layout, null);
            PublishPictureViewHolder viewHolder = new PublishPictureViewHolder(view);
            return viewHolder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof PublishPictureAddViewHolder) {
            ((PublishPictureAddViewHolder)viewHolder).setData(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (picOperation != null) {
                        picOperation.addPic();
                    }
                }
            });
        } else {
            ((PublishPictureViewHolder)viewHolder).setData(datas.get(position), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datas.remove(position);
                    if (datas.size() < COMMON_SIZE && datas.get(datas.size() - 1).picPath != null) {
                        datas.add(new PublishPicData(null));
                    }
                    notifyDataSetChanged();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (datas.get(position).picPath != null) {
            return NORMAL_TYPE;
        }
        return ADD_TYPE;
    }

    public interface PicOperation {
        void addPic();
    }
}
