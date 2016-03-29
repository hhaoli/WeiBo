package com.lihonghao.weibo.core;

import android.support.v7.widget.Toolbar;

import com.lihonghao.weibo.R;
import com.lihonghao.weibo.core.BaseFragment;

import butterknife.Bind;

public abstract class BaseToolbarFragment extends BaseFragment {
    @Bind(R.id.Toolbar)
    protected Toolbar mToolbar;

    @Override
    protected void init() {
        initToolbar();
        setHasOptionsMenu(true);
    }

    protected void initToolbar() {

    }
}
