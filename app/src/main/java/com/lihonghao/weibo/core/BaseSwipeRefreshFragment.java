package com.lihonghao.weibo.core;

import android.support.v4.widget.SwipeRefreshLayout;

import com.lihonghao.weibo.R;

import butterknife.Bind;

public abstract class BaseSwipeRefreshFragment extends BaseToolbarFragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.SwipeRefreshLayout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void init() {
        super.init();
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
    }

    protected void setRefreshing() {
        mSwipeRefreshLayout.post(() -> {
            mSwipeRefreshLayout.setRefreshing(true);
            onRefresh();
        });
    }

    protected boolean isRefreshing() {
        return mSwipeRefreshLayout.isRefreshing();
    }

    protected void stopRefreshing() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
