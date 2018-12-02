package com.jinhong.baidu;

import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.search.core.PoiInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrc on 2018/11/7.
 */

public interface PoiSearchCallback {
    void searchCallback(ArrayList<OverlayOptions> preOverlayOptions, List<OverlayOptions> overlayOptions,  List<PoiInfo> pois);
}
