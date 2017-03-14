package com.example.mvp.mymvp.contract;

import com.example.mvp.mymvp.bean.SampleInfo;

/**
 * Created by Afei on 2017/3/11.
 */

public interface SampleContract {
    interface View{
        void showSmple(SampleInfo info);
        void errorGetSample(String msg);
    }

    interface Presenter{
        void geteNewestSample();
    }
}
