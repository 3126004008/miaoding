package com.jinhong.miaoding.ui.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jinhong.miaoding.R;
import com.jinhong.miaoding.data.NearbyAddressData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chrc on 2018/11/6.
 */

public class NearbySearchViewHolder extends RecyclerView.ViewHolder {

    TextView addressTv;
    TextView addressDescTv;
    TextView selectorTv;

    public NearbySearchViewHolder(@NonNull View itemView) {
        super(itemView);
        addressTv = itemView.findViewById(R.id.tv_address);
        addressDescTv = itemView.findViewById(R.id.tv_address_desc);
        selectorTv = itemView.findViewById(R.id.tv_selector);
    }

    public void setData(PoiInfo data) {
        addressTv.setText(data.address);
        addressDescTv.setText(data.area);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectorTv.setSelected(true);
            }
        });
    }
}
