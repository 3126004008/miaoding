package com.jinhong.miaoding.event;

import com.baidu.mapapi.search.core.PoiInfo;

import java.util.List;

/**
 * Created by chrc on 2018/11/7.
 */

public class NearbySearchResultEvent {

    public List<PoiInfo> poiInfos;

    public NearbySearchResultEvent(List<PoiInfo> poiInfos) {
        this.poiInfos = poiInfos;
    }
}
