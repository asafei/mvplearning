package com.example.mvp.mymvp.presenter;

import com.example.mvp.mymvp.api.BaseApi;
import com.example.mvp.mymvp.api.SampleAPI;
import com.example.mvp.mymvp.bean.GetSampleInfoRet;
import com.example.mvp.mymvp.bean.SampleInfo;
import com.example.mvp.mymvp.cache.SampleCache;
import com.example.mvp.mymvp.contract.SampleContract;

import java.io.Serializable;

/**
 *
 * 负责从model层获取数据、整理数据、行为处理等，
 * 处理后调用view显示数据
 * Created by Afei on 2017/3/11.
 */

public class SamplePresenter implements SampleContract.Presenter {
    /**
     * 思考问题：
     * 一、SamplePresenter 在getNeweatSample()中
     * 完成对数据的请求
     *  1、优先从缓存中获取数据，触发view的showSample()函数，
     *  2、其次从网络上获取数据
     *      获取数据之后保存到缓存中，触发view的showSample()函数，
     *
     * 从而完成数据的展示
     *
     * 二、可是SamplePresenter 在什么时候触发getNeweatSample()呢？
     *      肯定是和view形成回调，在view中相应的地方天际监听，触发该函数
     *
     *
     *
     */

    private SampleContract.View view;
    private SampleAPI mApi;
    private SampleCache mCache;


    public SamplePresenter(SampleContract.View view) {
        this.view = view;
        mApi=new SampleAPI();
        mCache=new SampleCache();
    }

    @Override
    public void geteNewestSample() {
        //先从缓存中获取最新消息
        SampleInfo sampleInfo=mCache.getNewestSampleInfo();

        //如果缓存为空，则联网获取信息
        if (sampleInfo==null){
            mApi.getSampleInfo("uid", new BaseApi.ApiCallback<GetSampleInfoRet>() {
                @Override
                public void onSuccess(GetSampleInfoRet ret) {
                    //数据获取成功，先保存到缓存中，然后在view中展示这些数据
                    mCache.saveNewestSample((Serializable) ret.data);
                    view.showSmple(ret.data);
                }

                @Override
                public void onError(int err_code, String err_msg) {
                    //服务端返回错误码
                    view.errorGetSample(err_msg);
                }

                @Override
                public void onFailue() {
                    //网络请求或者解析错误
                    view.errorGetSample("服务其请求错误");
                }
            });
        }else {
            //将获取的信息展示在view中
            view.showSmple(sampleInfo);
        }



    }
}
