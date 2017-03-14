package com.example.mvp.mymvp.cache;

import android.content.Context;

import com.example.mvp.mymvp.bean.SampleInfo;

import java.io.Serializable;

/**
 * Created by Afei on 2017/3/11.
 */

public class SampleCache {

    private final String KRY_NEWEST_SAMPLE_INFO="samle_newest_info";

    public SampleCache() {

    }

    public void saveNewestSample(Serializable serializable){
        //保存最新信息到缓存中

    }

    public SampleInfo getNewestSampleInfo(){
        //获取缓存中的最新信息
        return null;
    }

    public void removeNewestSampleInfo(){
        //移除缓存中的最新信息
    }
}
