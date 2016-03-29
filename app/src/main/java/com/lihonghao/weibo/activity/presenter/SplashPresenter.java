package com.lihonghao.weibo.activity.presenter;

import android.os.Handler;

import com.lihonghao.weibo.activity.view.SplashView;
import com.lihonghao.weibo.core.mvp.BasePresenter;
import com.lihonghao.weibo.data.DataManager;
import com.lihonghao.weibo.entity.LoginEntity;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class SplashPresenter extends BasePresenter<SplashView> {
    private final DataManager mDataManager;
    private CompositeSubscription mSubscription;

    @Inject
    public SplashPresenter(DataManager dataManager) {
        mDataManager = dataManager;

    }

    public void init(int taskId) {
        mSubscription = mDataManager.getSubscription(taskId);

        if (!getMvpView().checkNet()) {//没有网络
            getMvpView().settingNet();
            return;
        }

        if (checkVersion()) {//有新版本
            getMvpView().updateVersion();
            return;
        }

        if (!getMvpView().isFirst()) {//第一次使用
            getMvpView().toGuide();
            return;
        }

        new Handler().postDelayed(() -> getMvpView().toMain(), 2000L);
    }

    private boolean checkVersion() {

        mSubscription.add(mDataManager.login("demo", "p@ssw0rd")
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<LoginEntity>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(LoginEntity entity) {

                            }
                        })
        );
        return false;
    }
}
