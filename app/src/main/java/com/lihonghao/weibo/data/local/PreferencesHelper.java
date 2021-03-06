package com.lihonghao.weibo.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lihonghao.weibo.Config;
import com.lihonghao.weibo.injection.ApplicationContext;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferencesHelper {

    private final SharedPreferences mPref;
    private final Gson mGson;

    private static final String PREFERENCES_NAME = "com_weibo_sdk_android";


    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        mPref = context.getSharedPreferences(Config.PREF_FILE_NAME, Context.MODE_PRIVATE);
        mGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz")
                .create();
    }

    public void putString(String key, String value) {
        mPref.edit().putString(key, value).apply();
    }

    @Nullable
    public String getString(String key) {
        return mPref.getString(key, null);
    }

    public void putInt(String key, int value) {
        mPref.edit().putInt(key, value).apply();
    }

    public int getInt(String key) {
        return mPref.getInt(key, 0);
    }

    public void putBoolean(String key, boolean value) {
        mPref.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return mPref.getBoolean(key, false);
    }

    public void putLong(String key, long value) {
        mPref.edit().putLong(key, value).apply();
    }

    public long getLong(String key) {
        return mPref.getLong(key, 0);
    }

    /**
     * 移除一项
     */
    public boolean remove(String key) {
        return mPref.edit().remove(key).commit();
    }

    /**
     * 清除文件内容
     */
    public void clear() {
        mPref.edit().clear().apply();
    }

    /**
     * 某一项是否存在
     */
    public boolean contatins(String key) {
        return mPref.contains(key);
    }

    /**
     * 保存实体类UserBean
     */
    public void putUserBean(UserBean bean) {
        SharedPreferences.Editor editor = mPref.edit();
        editor.putString("UserBean", mGson.toJson(bean));
        editor.apply();
    }

    /**
     * 获取实体类UserBean
     */
    public UserBean getUserBean() {
        String beanJson = mPref.getString("UserBean", null);
        if (beanJson != null) {
            return mGson.fromJson(beanJson, UserBean.class);
        }
        return null;
    }

    /**
     * 保存 Token 对象到 SharedPreferences。
     */
    public void writeAccessToken(Oauth2AccessToken token) {
        SharedPreferences.Editor editor = mPref.edit();
        editor.putString(Config.KEY_UID, token.getUid());
        editor.putString(Config.KEY_ACCESS_TOKEN, token.getToken());
        editor.putLong(Config.KEY_EXPIRES_IN, token.getExpiresTime());
        editor.apply();
    }
    /**
     * 读取 Token 信息。
     */
    public Oauth2AccessToken readAccessToken() {
        Oauth2AccessToken token = new Oauth2AccessToken();
        token.setUid(mPref.getString(Config.KEY_UID, ""));
        token.setToken(mPref.getString(Config.KEY_ACCESS_TOKEN, ""));
        token.setExpiresTime(mPref.getLong(Config.KEY_EXPIRES_IN, 0));
        return token;
    }

}
