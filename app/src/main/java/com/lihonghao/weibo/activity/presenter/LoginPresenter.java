package com.lihonghao.weibo.activity.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.lihonghao.weibo.Config;
import com.lihonghao.weibo.activity.LoginActivity;
import com.lihonghao.weibo.activity.view.LoginView;
import com.lihonghao.weibo.core.mvp.BasePresenter;
import com.lihonghao.weibo.data.DataManager;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<LoginView> {

    private AuthInfo mAuthInfo;

    /**
     * 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能
     */
    private Oauth2AccessToken mAccessToken;

    /**
     * 注意：SsoHandler 仅当 SDK 支持 SSO 时有效
     */
    private SsoHandler mSsoHandler;

    @Inject
    DataManager mDataManager;

    @Inject
    public LoginPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    public void init(Context context) {
        mAuthInfo = new AuthInfo(context, Config.APP_KEY, Config.REDIRECT_URL, Config.SCOPE);
        mSsoHandler = new SsoHandler((LoginActivity) context, mAuthInfo);
    }

    public void authorize() {
        mSsoHandler.authorize(new AuthListener());
    }

    class AuthListener implements WeiboAuthListener {
        @Override
        public void onComplete(Bundle bundle) {
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(bundle);
            if (mAccessToken.isSessionValid()) {
                // 保存 Token 到 SharedPreferences
                mDataManager.getPreferencesHelper().writeAccessToken(mAccessToken);
                getMvpView().showToast("授权成功");
                getMvpView().toMain();
            } else {
                // 以下几种情况，您会收到 Code：
                // 1. 当您未在平台上注册的应用程序的包名与签名时；
                // 2. 当您注册的应用程序包名与签名不正确时；
                // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
                String code = bundle.getString("code");
                String message = "授权失败";
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code;
                }
                getMvpView().showToast(message);
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
            getMvpView().showToast("授权异常 : " + e.getMessage());
        }

        @Override
        public void onCancel() {
            getMvpView().showToast("取消授权");
        }
    }
}
