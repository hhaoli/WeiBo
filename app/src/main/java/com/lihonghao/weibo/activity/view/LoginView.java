package com.lihonghao.weibo.activity.view;

import com.lihonghao.weibo.core.mvp.MvpView;

public interface LoginView extends MvpView {

    void toMain();

    void showToast(String msg);
}
