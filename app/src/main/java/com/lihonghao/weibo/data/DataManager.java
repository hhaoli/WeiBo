package com.lihonghao.weibo.data;

import com.lihonghao.weibo.data.local.DatabaseHelper;
import com.lihonghao.weibo.data.local.PreferencesHelper;
import com.lihonghao.weibo.data.remote.APIService;
import com.lihonghao.weibo.entity.LoginEntity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

@Singleton
public class DataManager {

    private final APIService mAPIService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final EventPosterHelper mEventPosterHelper;
    private Map<Integer, CompositeSubscription> mSubscriptionMap;

    @Inject
    public DataManager(APIService apiService, DatabaseHelper databaseHelper, PreferencesHelper preferencesHelper, EventPosterHelper eventPosterHelper) {
        mAPIService = apiService;
        mDatabaseHelper = databaseHelper;
        mPreferencesHelper = preferencesHelper;
        mEventPosterHelper = eventPosterHelper;
        mSubscriptionMap = new HashMap<>();
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

    public EventPosterHelper getEventPosterHelper() {
        return mEventPosterHelper;
    }

    public void addSubscription(int taskId) {
        CompositeSubscription subscription;
        if (mSubscriptionMap.get(taskId) == null) {
            subscription = new CompositeSubscription();
            mSubscriptionMap.put(taskId, subscription);
        }
    }

    public void removeSubscription(int taskId) {
        CompositeSubscription subscription;
        if (mSubscriptionMap != null && mSubscriptionMap.get(taskId) != null) {
            subscription = mSubscriptionMap.get(taskId);
            subscription.unsubscribe();
            mSubscriptionMap.remove(taskId);
        }
    }

    public CompositeSubscription getSubscription(int taskId) {
        CompositeSubscription compositeSubscription;
        if (mSubscriptionMap.get(taskId) == null) {
            compositeSubscription = new CompositeSubscription();
            mSubscriptionMap.put(taskId, compositeSubscription);
        } else {
            compositeSubscription = mSubscriptionMap.get(taskId);
        }
        return compositeSubscription;
    }

    public Observable<LoginEntity> login(String username, String password) {
        return mAPIService.login(username, password, "android").doOnNext(entity -> {

        });
    }
}
