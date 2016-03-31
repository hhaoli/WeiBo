package com.lihonghao.weibo.fragment;

import android.support.v4.app.Fragment;

import com.lihonghao.weibo.R;
import com.lihonghao.weibo.core.BaseFragment;


public class MessageFragment extends BaseFragment {

    public static MessageFragment newInstance() {
        MessageFragment fragment = new MessageFragment();
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
