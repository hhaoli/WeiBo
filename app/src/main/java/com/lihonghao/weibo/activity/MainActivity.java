package com.lihonghao.weibo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lihonghao.weibo.R;
import com.lihonghao.weibo.core.BaseActivity;
import com.lihonghao.weibo.data.DataManager;
import com.lihonghao.weibo.fragment.DiscoverFragment;
import com.lihonghao.weibo.fragment.HomeFragment;
import com.lihonghao.weibo.fragment.MessageFragment;
import com.lihonghao.weibo.fragment.SelfFragment;

import javax.inject.Inject;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Inject
    DataManager mDataManager;

    private HomeFragment mHomeFragment;
    private MessageFragment mMessageFragment;
    private DiscoverFragment mDiscoverFragment;
    private SelfFragment mSelfFragment;
    private FragmentManager mFragmentManager;
    private static final String INDEX = "index";//主页底部导航栏标记,用于跳转回主页的时候调用
    private int index = 0;

    public static void launch(AppCompatActivity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        activityComponent().inject(this);
        mFragmentManager = getSupportFragmentManager();
        onTabSelected(0);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int position = intent.getIntExtra(INDEX, 0);
        onTabSelected(position);
    }

    private void onTabSelected(int position) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();//必须放在这里,每次调用都要实例化
        hideFragment(transaction);
        switch (position) {
            case 0:
                if (null == mHomeFragment) {
                    mHomeFragment = HomeFragment.newInstance();
                    transaction.add(R.id.main_container, mHomeFragment);
                } else {
                    transaction.show(mHomeFragment);
                }
                break;
            case 1:
                if (null == mMessageFragment) {
                    mMessageFragment = MessageFragment.newInstance();
                    transaction.add(R.id.main_container, mMessageFragment);
                } else {
                    transaction.show(mMessageFragment);
                }
                break;
            case 2:
                if (null == mDiscoverFragment) {
                    mDiscoverFragment = DiscoverFragment.newInstance();
                    transaction.add(R.id.main_container, mDiscoverFragment);
                } else {
                    transaction.show(mDiscoverFragment);
                }
                break;
            case 3:
                if (null == mSelfFragment) {
                    mSelfFragment = SelfFragment.newInstance();
                    transaction.add(R.id.main_container, mSelfFragment);
                } else {
                    transaction.show(mSelfFragment);
                }
                break;
        }
        index = position;
        transaction.commitAllowingStateLoss();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mMessageFragment != null) {
            transaction.hide(mMessageFragment);
        }
        if (mDiscoverFragment != null) {
            transaction.hide(mDiscoverFragment);
        }
        if (mSelfFragment != null) {
            transaction.hide(mSelfFragment);
        }
    }

    public void onCheck() {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
