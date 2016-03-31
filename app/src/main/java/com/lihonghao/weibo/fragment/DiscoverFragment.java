package com.lihonghao.weibo.fragment;

import com.lihonghao.weibo.R;
import com.lihonghao.weibo.core.BaseFragment;


public class DiscoverFragment extends BaseFragment {

    public static DiscoverFragment newInstance() {
        DiscoverFragment fragment = new DiscoverFragment();
        return fragment;
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_message;
    }

    @Override
    protected void init() {

    }
}
