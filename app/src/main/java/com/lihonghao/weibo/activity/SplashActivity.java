package com.lihonghao.weibo.activity;

import android.os.Bundle;
import android.util.Log;

import com.lihonghao.weibo.R;
import com.lihonghao.weibo.activity.presenter.SplashPresenter;
import com.lihonghao.weibo.activity.view.SplashView;
import com.lihonghao.weibo.core.BaseActivity;
import com.lihonghao.weibo.data.DataManager;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashView {

    @Inject
    DataManager mDataManager;
    @Inject
    SplashPresenter mPresenter;

    @Override
    protected int layoutResID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        activityComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.init(getTaskId());
    }

    @Override
    public boolean checkNet() {
        return true;
    }

    @Override
    public void settingNet() {

    }

    @Override
    public boolean isFirst() {
        return false;
    }

    @Override
    public void updateVersion() {

    }

    @Override
    public void toMain() {
        MainActivity.launch(this);
    }

    @Override
    public void toGuide() {

    }

    @Override
    public void onFailure(Throwable e) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
