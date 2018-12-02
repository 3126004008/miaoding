package com.jinhong.miaoding.data;

import com.jinhong.miaoding.base.BaseData;

/**
 * Created by chrc on 2018/11/7.
 */

public class NearbyAddressData extends BaseData {

    public String address;
    public String addressDesc;

    public NearbyAddressData(String address, String addressDesc) {
        this.address = address;
        this.addressDesc = addressDesc;
    }
}
