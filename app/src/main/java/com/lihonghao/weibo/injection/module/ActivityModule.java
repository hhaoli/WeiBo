package com.lihonghao.weibo.injection.module;

import android.app.Activity;
import android.content.Context;

import com.lihonghao.weibo.injection.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity providesActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context providesContext(){
        return mActivity;
    }
}
