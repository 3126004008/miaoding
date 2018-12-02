package com.jinhong.miaoding.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.search.core.PoiInfo;
import com.jinhong.miaoding.R;
import com.jinhong.miaoding.data.NearbyAddressData;
import com.jinhong.miaoding.ui.viewholder.NearbySearchViewHolder;
import com.jinhong.miaoding.ui.viewholder.PublishEmojiViewHolder;

import java.util.List;

/**
 * Created by chrc on 2018/11/6.
 */

public class NearchbySearchAdapter extends RecyclerView.Adapter {

    Context context;
    List<PoiInfo> data;

    public NearchbySearchAdapter(Context context, List<PoiInfo> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nearby_search_layout, null);
        NearbySearchViewHolder viewHolder = new NearbySearchViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ((NearbySearchViewHolder)viewHolder).setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
}
