package com.example.mvp.mymvp.api;

import com.example.mvp.mymvp.bean.GetSampleInfoRet;

/**
 * Created by Afei on 2017/3/11.
 */

public class SampleAPI extends BaseApi{
    private static final String baseUrl="http://www.baidu.com";
    private ApiStore apiStore;

    public SampleAPI() {
        apiStore=mRetrofit.create(ApiStore.class);
    }


    public void getSampleInfo(String uid, BaseApi.ApiCallback<GetSampleInfoRet> callback){}

    public interface ApiStore{

    }
}
