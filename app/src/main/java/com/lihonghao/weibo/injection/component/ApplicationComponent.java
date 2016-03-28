package com.lihonghao.weibo.injection.component;

import android.app.Application;
import android.content.Context;

import com.lihonghao.weibo.MyApplication;
import com.lihonghao.weibo.data.APIService;
import com.lihonghao.weibo.data.DataManager;
import com.lihonghao.weibo.data.DatabaseHelper;
import com.lihonghao.weibo.data.PreferencesHelper;
import com.lihonghao.weibo.data.UnauthorisedInterceptor;
import com.lihonghao.weibo.injection.ApplicationContext;
import com.lihonghao.weibo.injection.module.ApplicationModule;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  void inject(MyApplication application);

    void inject(UnauthorisedInterceptor interceptor);

    @ApplicationContext
    Context context();

    Application application();

    APIService apiService();

    PreferencesHelper preferencesHelper();

    DatabaseHelper databaseHelper();

    DataManager dataManger();

    Bus bus();
}
