package com.lihonghao.weibo.activity;

import android.os.Bundle;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.lihonghao.weibo.R;
import com.lihonghao.weibo.activity.presenter.SplashPresenter;
import com.lihonghao.weibo.activity.view.SplashView;
import com.lihonghao.weibo.core.BaseActivity;
import com.lihonghao.weibo.util.NetUtils;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashView {

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
        mPresenter.init();
    }

    @Override
    public boolean checkNet() {
        return NetUtils.isConnected(this);
    }

    @Override
    public void settingNet() {
        new AlertDialogWrapper.Builder(this).setTitle("提示").setMessage("是否开启网络?")
                .setPositiveButton("是", (dialog, which) -> {
                    NetUtils.openNetSetting(SplashActivity.this);
                    dialog.dismiss();
                    finish();
                })
                .setNegativeButton("否", (dialog, which) -> {
                    toMain();
                    dialog.dismiss();
                    finish();
                }).create().show();
    }

    @Override
    public void toLogin() {
        LoginActivity.launch(this);
        finish();
    }

    @Override
    public void toMain() {
        MainActivity.launch(this);
        finish();
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
