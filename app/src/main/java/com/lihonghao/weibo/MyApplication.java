package com.lihonghao.weibo;

import android.app.Application;
import android.content.Context;

import com.lihonghao.weibo.data.DataManager;
import com.lihonghao.weibo.injection.component.ApplicationComponent;
import com.lihonghao.weibo.injection.component.DaggerApplicationComponent;
import com.lihonghao.weibo.injection.module.ApplicationModule;
import com.squareup.otto.Bus;

import javax.inject.Inject;

public class MyApplication extends Application {

    @Inject
    Bus mBus;
    @Inject
    DataManager mDataManager;
    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
        mBus.register(this);
    }

    public static MyApplication get(Context context){
        return (MyApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
