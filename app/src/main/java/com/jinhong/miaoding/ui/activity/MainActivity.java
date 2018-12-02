package com.jinhong.miaoding.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.search.core.PoiInfo;
import com.jinhong.baidu.BaiduMapHelper;
import com.jinhong.baidu.BaiduMapUtils;
import com.jinhong.baidu.PoiSearchCallback;
import com.jinhong.miaoding.R;
import com.jinhong.miaoding.ViewPagerScroller;
import com.jinhong.miaoding.base.BaseActivity;
import com.jinhong.miaoding.event.NearbySearchEvent;
import com.jinhong.miaoding.event.NearbySearchResultEvent;
import com.jinhong.miaoding.ui.fragment.ResourceDetailFragment;
import com.jinhong.miaoding.ui.popwindow.PublishPopWindow;
import com.jinhong.miaoding.ui.view.LinkViewPager;
import com.jinhong.miaoding.ui.view.LinkedRelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity implements PoiSearchCallback {

    @BindView(R.id.bmapView)
    MapView mMapView;
    @BindView(R.id.ll_title_bar)
    LinearLayout titleLayout;
    @BindView(R.id.viewpager)
    LinkViewPager mViewPager;
    @BindView(R.id.relative_link)
    LinkedRelativeLayout mLinkRelativeLayout;

    private BaiduMapHelper baiduMapHelper;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        unbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
        baiduMapHelper.initBaiduMap();
        requestPermission();
    }

    private void initView() {
        baiduMapHelper = new BaiduMapHelper(this, mMapView, this);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                Bundle bundle = new Bundle();
                bundle.putString(ResourceDetailFragment.CONTENT_KEY, ""+i);
                return ResourceDetailFragment.newInstance(bundle);
            }

            @Override
            public int getCount() {
                return 4;
            }
        });

        mLinkRelativeLayout.setFollowViewPager(mViewPager);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        },1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        baiduMapHelper.initLocaltion();
    }


    @OnClick({R.id.ll_title_bar, R.id.iv_filter})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_title_bar:
               new PublishPopWindow(MainActivity.this, mMapView);
                break;
            case R.id.iv_filter:
                Intent intent = new Intent(this, FilterActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void searchCallback(ArrayList<OverlayOptions> preOverlayOptions, List<OverlayOptions> overlayOptions, List<PoiInfo> pois) {
        EventBus.getDefault().post(new NearbySearchResultEvent(pois));
    }


    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMain(NearbySearchEvent event) {
        BaiduMapUtils.getAdressFromLatLng(mMapView.getMap(), baiduMapHelper.getmCurrLatLng(), this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        EventBus.getDefault().unregister(this);
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
