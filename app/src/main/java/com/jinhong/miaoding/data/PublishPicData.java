package com.jinhong.miaoding.data;

import android.net.Uri;

import com.jinhong.miaoding.base.BaseData;

/**
 * Created by chrc on 2018/11/6.
 */

public class PublishPicData extends BaseData {

    public Uri picPath;

    public PublishPicData(Uri picPath) {
        this.picPath = picPath;
    }
}
