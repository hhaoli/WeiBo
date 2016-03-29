package com.lihonghao.weibo.core.mvp;

public interface Presenter<V extends MvpView> {
    void attachView(V mvpView);

    void detachView();
}
