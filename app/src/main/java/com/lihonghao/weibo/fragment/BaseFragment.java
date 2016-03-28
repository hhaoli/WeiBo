package com.lihonghao.weibo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lihonghao.weibo.activity.BaseAppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    private int taskId;
    protected View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setReturnTransition(true);
        ((BaseAppCompatActivity) getActivity()).activityComponent().inject(this);
        taskId = getActivity().getTaskId();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(layoutResID(), container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        initView(view,savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        initData(savedInstanceState);
        init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract int layoutResID();

    protected abstract void init();
}
