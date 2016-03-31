package com.lihonghao.weibo;

public class Config {
    //API
    public static final String ENDPOINT = "http://openapi.demo.com/";
    public static final String API_LOGIN = "passport/login";
    //pref
    public static final String PREF_FILE_NAME = "config";
    public static final String PREF_KEY_FIRST = "pref_key_first";
    //WeiBoSDK
    public static final String KEY_UID = "uid";
    public static final String KEY_ACCESS_TOKEN = "access_token";
    public static final String KEY_EXPIRES_IN = "expires_in";
    public static final String APP_KEY = "1297113687";
    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";
    public static final String SCOPE = "email,direct_messages_read,direct_messages_write,friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog," + "invitation_write";
}
