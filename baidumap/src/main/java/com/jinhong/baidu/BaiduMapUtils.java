package com.jinhong.baidu;

import android.util.Log;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrc on 2018/11/5.
 */

public class BaiduMapUtils {

    private static ArrayList<OverlayOptions> preOverlayOptions = new ArrayList<>();

    public static void getAdressFromLatLng(final BaiduMap baiduMap, LatLng latLng, final PoiSearchCallback poiSearchCallback) {
        GeoCoder geoCoder = GeoCoder.newInstance();

        // 设置地址或经纬度反编译后的监听,这里有两个回调方法
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    //找不到该地址!;
                } else {
                    pointSearch(baiduMap, result.getLocation(), result.getAddress(), poiSearchCallback);
                    Log.i("location===rr", " address=" + result.getAddress());
                }
            }

            @Override
            public void onGetGeoCodeResult(GeoCodeResult result) {
                // 详细地址转换在经纬度
                pointSearch(baiduMap, result.getLocation(), result.getAddress(), poiSearchCallback);
                Log.i("location===rg"," address="+result.getAddress());

            }
        });
        // 设置反地理经纬度坐标,请求位置时,需要一个经纬度
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
    }

    private static void pointSearch(final BaiduMap baiduMap, LatLng latLng, String keyword, final PoiSearchCallback poiSearchCallback) {
        PoiSearch poiSearch = PoiSearch.newInstance();
        OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener(){
            public void onGetPoiResult(PoiResult result){
                //获取POI检索结果
                List<PoiInfo> pois = result.getAllPoi();
                if (pois == null) {
                    return;
                }

                List<OverlayOptions> overlayOptions = new ArrayList<>();
                Log.i("location===ps", "pois="+ pois);
                for (int i = 0; i < pois.size(); i++) {
                    PoiInfo poiInfo = pois.get(i);
                    overlayOptions.add(
                            new MarkerOptions()
                                    .position(poiInfo.location)
                                    .icon(BitmapDescriptorFactory
                                            .fromResource(R.drawable.icon_mark1)));
                }
                poiSearchCallback.searchCallback(preOverlayOptions, overlayOptions, pois);
//                if (preOverlayOptions.size() > 0) {
//                    baiduMap.clear();
//                }

//                preOverlayOptions.clear();
//                preOverlayOptions.addAll(overlayOptions);
//                baiduMap.addOverlays(overlayOptions);

            }
            public void onGetPoiDetailResult(PoiDetailResult result){
                //获取Place详情页检索结果
                Log.i("location===ps", "pois="+ result.toString());
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        };
        poiSearch.setOnGetPoiSearchResultListener(poiListener);
        poiSearch.searchNearby(new PoiNearbySearchOption()
                .location(latLng)
                .keyword(keyword)
                .radius(5000)
                .pageNum(0));
    }
}
