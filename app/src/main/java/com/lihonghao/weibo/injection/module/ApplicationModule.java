package com.lihonghao.weibo.injection.module;

import android.app.Application;
import android.content.Context;

import com.lihonghao.weibo.data.APIService;
import com.lihonghao.weibo.injection.ApplicationContext;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context providesContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    Bus providesBus() {
        return new Bus();
    }

    @Provides
    @Singleton
    APIService providesAPIService() {
        return APIService.Factory.makeAPIService(mApplication);
    }
}
