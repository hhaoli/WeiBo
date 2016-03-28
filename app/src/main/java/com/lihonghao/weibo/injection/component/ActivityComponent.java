package com.lihonghao.weibo.injection.component;


import com.lihonghao.weibo.activity.MainActivity;
import com.lihonghao.weibo.activity.SplashActivity;
import com.lihonghao.weibo.fragment.BaseFragment;
import com.lihonghao.weibo.injection.PerActivity;
import com.lihonghao.weibo.injection.module.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(SplashActivity activity);

    void inject(MainActivity activity);

    void inject(BaseFragment fragment);
}
