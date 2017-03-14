package com.example.mvp.mymvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mvp.R;
import com.example.mvp.mymvp.bean.SampleInfo;
import com.example.mvp.mymvp.contract.SampleContract;
import com.example.mvp.mymvp.presenter.SamplePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements SampleContract.View{
    /**
     * 思考问题：LoginActivity中的showSample()函数在什么时候触发呢？
     *  通过将本view传给mPresenter，用mPresenter触发view的showSample()函数
     *
     */

    @BindView(R.id.et_username) EditText et_username;
    @BindView(R.id.et_password) EditText et_password;
    @BindView(R.id.tv_result) TextView tv_result;
    @OnClick(R.id.btn_login) void clickLogin(){
        //步骤一、从这里引出对数据的请求，染p中回调view的showSample()方法；
        mPresenter.geteNewestSample();
    }

    private SampleContract.Presenter mPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        mPresenter=new SamplePresenter(this);
    }






    //下面的调用，不归本activity管，是在mPresenter中控制
    //在这里处理UI的数据
    @Override
    public void showSmple(SampleInfo info) {
        tv_result.setText(info.result);
    }

    @Override
    public void errorGetSample(String msg) {
        //错误信息
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
