package com.lihonghao.weibo.activity.view;

import com.lihonghao.weibo.core.mvp.MvpView;

public interface SplashView extends MvpView {
    boolean checkNet();

    void settingNet();

    boolean isFirst();

    void updateVersion();

    void toMain();

    void toGuide();
}
