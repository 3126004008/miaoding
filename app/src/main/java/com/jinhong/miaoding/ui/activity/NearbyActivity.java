package com.jinhong.miaoding.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.google.gson.Gson;
import com.jinhong.miaoding.R;
import com.jinhong.miaoding.base.BaseActivity;
import com.jinhong.miaoding.data.NearbyAddressData;
import com.jinhong.miaoding.event.NearbySearchEvent;
import com.jinhong.miaoding.event.NearbySearchResultEvent;
import com.jinhong.miaoding.ui.adapter.NearchbySearchAdapter;
import com.jinhong.miaoding.ui.fragment.PublishEmojiFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chrc on 2018/11/5.
 * Desc: 附近地址
 */

public class NearbyActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.et_search)
    EditText searchEt;
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;

    RecyclerView.Adapter mAdapter;
    ArrayList<PoiInfo> datas;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_nearby_layout);
        unbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
        locate();
    }

    private void locate() {
        EventBus.getDefault().post(new NearbySearchEvent());
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        datas = new ArrayList<>();
        mAdapter = new NearchbySearchAdapter(this, datas);
        recyclerView.setAdapter(mAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NearbySearchResultEvent event) {
        if (event.poiInfos != null) {
            if (datas == null) {
                datas = new ArrayList<>();
            }
            datas.clear();
            datas.addAll(event.poiInfos);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (unbinder != null) {
            unbinder.unbind();
        }
        overridePendingTransition(0, R.anim.from_top_to_bottom);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                Intent intent = new Intent(this, PublishActivity.class);
                startActivity(intent);
                break;
        }
    }
}
