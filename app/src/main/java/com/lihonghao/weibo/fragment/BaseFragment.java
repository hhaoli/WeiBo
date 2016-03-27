package com.lihonghao.weibo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setReturnTransition(true);
//        ((BaseAppCompatActivity)getActivity()).activityComponent().inject(this);
    }
}
