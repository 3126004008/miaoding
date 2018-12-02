package com.jinhong.miaoding.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jinhong.miaoding.R;
import com.jinhong.miaoding.event.FilterEvent;
import com.jinhong.miaoding.ui.view.FilterView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.logging.Filter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FilterActivity extends AppCompatActivity {

    @BindView(R.id.view_explore)
    FilterView filterExplore;
    @BindView(R.id.view_nearby_person)
    FilterView filterPerson;
    @BindView(R.id.view_nearby_thing)
    FilterView filterThing;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_filter);
        unbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    private void initView() {
    }

    private void initData() {
        filterExplore.setData(new String[]{"全部", "我的好友", "我的关注"}, FilterView.EXPLORE);
        filterPerson.setData(new String[]{"全部", "男性", "女性"}, FilterView.NEARBY_PERSON);
        filterThing.setData(new String[]{"自动筛选", "说说", "好吃", "好玩"}, FilterView.NEARBY_THING);
    }

    @OnClick({R.id.iv_filter_back, R.id.tv_filter_back, R.id.tv_filter_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_filter_back:
                finish();
                break;
            case R.id.tv_filter_back:
                finish();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEvent(FilterEvent event) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
