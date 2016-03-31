package com.lihonghao.weibo.activity.presenter;

import android.os.Handler;

import com.lihonghao.weibo.activity.view.SplashView;
import com.lihonghao.weibo.core.mvp.BasePresenter;
import com.lihonghao.weibo.data.DataManager;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import javax.inject.Inject;

public class SplashPresenter extends BasePresenter<SplashView> {

    private Oauth2AccessToken accessToken;
    @Inject
    DataManager mDataManager;

    @Inject
    public SplashPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    public void init() {


        if (!getMvpView().checkNet()) {//没有网络
            getMvpView().settingNet();
            return;
        }

        accessToken = mDataManager.getPreferencesHelper().readAccessToken();
        if (accessToken.isSessionValid()) {
            new Handler().postDelayed(() -> getMvpView().toMain(), 1000L);
        } else {
            new Handler().postDelayed(() -> getMvpView().toLogin(), 1000L);
        }
    }

//    private boolean checkVersion() {
//        mSubscription.add(mDataManager.login("demo", "p@ssw0rd")
//                        .subscribeOn(Schedulers.newThread())
//                        .subscribeOn(Schedulers.io())
//                        .unsubscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Observer<LoginEntity>() {
//                            @Override
//                            public void onCompleted() {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onNext(LoginEntity entity) {
//                                KLog.e(entity.toString());
//                            }
//                        })
//        );
//        return false;
//    }
}
