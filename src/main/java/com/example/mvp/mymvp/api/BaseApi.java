package com.example.mvp.mymvp.api;

import com.example.mvp.mymvp.bean.BaseRetData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Afei on 2017/3/11.
 */

public class BaseApi {
    private static final String mBaseURL="http://www.sina.com";
    protected Retrofit mRetrofit;

    private final String TAG="BaseApi";

    public BaseApi() {
        mRetrofit=new Retrofit.Builder()
                        .baseUrl(mBaseURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
    }

    public BaseApi(String baseURL) {
        mRetrofit=new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    //处理retrofit回调，并且调用ApiCallback相应返回
    protected class RetrofitCallback<T> implements Callback<T>{
        private ApiCallback<T> mCallback;

        public RetrofitCallback(ApiCallback<T> mCallback) {
            this.mCallback = mCallback;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (response.isSuccessful()){
                if (((BaseRetData)response.body()).ret==1){//当返回码为1时，得到正确的信息
                    mCallback.onSuccess((T)response.body());
                }else {//当返回码不为1时，得到正确的未必正确
                    mCallback.onError(((BaseRetData)response.body()).err_code,((BaseRetData)response.body()).err_msg);
                }

            }else {
                mCallback.onSuccess((T)response.body());
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            mCallback.onFailue();
        }
    }

    public interface ApiCallback<T>{
        void onSuccess(T ret);
        void onError(int err_code, String err_msg);
        void onFailue();
    }
}
