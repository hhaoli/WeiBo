package com.lihonghao.weibo.core;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lihonghao.weibo.MyApplication;
import com.lihonghao.weibo.data.DataManager;
import com.lihonghao.weibo.injection.component.ActivityComponent;
import com.lihonghao.weibo.injection.component.DaggerActivityComponent;
import com.lihonghao.weibo.injection.module.ActivityModule;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    private ActivityComponent mActivityComponent;
    @Inject
    DataManager mDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResID());
        ButterKnife.bind(this);
        activityComponent().inject(this);
        mDataManager.addSubscription(getTaskId());
        initToolbar(savedInstanceState);
        init(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDataManager.removeSubscription(getTaskId());
    }

    protected abstract int layoutResID();

    protected void initToolbar(Bundle savedInstanceState) {
    }

    protected abstract void init(Bundle savedInstanceState);


    public ActivityComponent activityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(MyApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }
}
