package com.lihonghao.weibo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.lihonghao.weibo.R;
import com.lihonghao.weibo.activity.presenter.LoginPresenter;
import com.lihonghao.weibo.activity.view.LoginView;
import com.lihonghao.weibo.core.BaseActivity;

import javax.inject.Inject;

import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginView {

    @Inject
    LoginPresenter mPresenter;

    public static void launch(AppCompatActivity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        activityComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.init(this);
    }

    @OnClick(R.id.btn_login)
    public void onLogin(View v) {
        mPresenter.authorize();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void toMain() {
        MainActivity.launch(this);
    }

    @Override
    public void showToast(String msg) {
        if (TextUtils.isEmpty(msg)) return;
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
