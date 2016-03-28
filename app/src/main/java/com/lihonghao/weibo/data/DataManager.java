package com.lihonghao.weibo.data;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataManager {

    private final APIService mAPIService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(APIService apiService, DatabaseHelper databaseHelper, PreferencesHelper preferencesHelper) {
        mAPIService = apiService;
        mDatabaseHelper = databaseHelper;
        mPreferencesHelper = preferencesHelper;
    }

    public APIService getAPIService() {
        return mAPIService;
    }

    public DatabaseHelper getDatabaseHelper() {
        return mDatabaseHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }
}
