package com.lihonghao.weibo.fragment;

import com.lihonghao.weibo.R;
import com.lihonghao.weibo.core.BaseFragment;


public class SelfFragment extends BaseFragment {

    public static SelfFragment newInstance() {
        SelfFragment fragment = new SelfFragment();
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
