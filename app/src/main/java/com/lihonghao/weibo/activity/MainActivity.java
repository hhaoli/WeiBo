package com.lihonghao.weibo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lihonghao.weibo.R;
import com.lihonghao.weibo.core.BaseActivity;
import com.lihonghao.weibo.data.DataManager;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    DataManager mDataManager;

    @Override
    protected int layoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        activityComponent().inject(this);
        Log.e("tag", "ID=" + getTaskId());
    }

    public static void launch(AppCompatActivity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDataManager.removeSubscription(getTaskId());
    }
}
