package com.lihonghao.weibo.fragment;

import android.support.v7.widget.Toolbar;

import com.lihonghao.weibo.R;

import butterknife.Bind;

public abstract class ToorbarBaseFragment extends BaseFragment{
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
