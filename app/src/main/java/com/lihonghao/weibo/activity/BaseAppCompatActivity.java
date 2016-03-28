package com.lihonghao.weibo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lihonghao.weibo.MyApplication;
import com.lihonghao.weibo.injection.component.ActivityComponent;
import com.lihonghao.weibo.injection.component.DaggerActivityComponent;
import com.lihonghao.weibo.injection.module.ActivityModule;

import butterknife.ButterKnife;

public abstract class BaseAppCompatActivity extends AppCompatActivity {
    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResID());
        ButterKnife.bind(this);
        initToolbar(savedInstanceState);
        initViews(savedInstanceState);
        initData();
        initListeners();
    }

    protected abstract int layoutResID();

    protected abstract void initToolbar(Bundle savedInstanceState);

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void initData();

    protected abstract void initListeners();

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
