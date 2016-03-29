package com.lihonghao.weibo.core;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.lihonghao.weibo.R;
import com.lihonghao.weibo.core.BaseActivity;

import butterknife.Bind;

public abstract class BaseToolbarActivity extends BaseActivity {

    @Bind(R.id.Toolbar)
    protected Toolbar mToolbar;
    @Bind(R.id.Title)
    protected TextView mTitle;
    @Bind(R.id.AppBarLayout)
    protected AppBarLayout mAppBarLayout;

    protected ActionBarHelper mActionBarHelper;

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        initToolbarHelper();
    }

    protected void initToolbarHelper() {
        if (this.mToolbar == null || this.mAppBarLayout == null)
            return;

        this.setSupportActionBar(this.mToolbar);

        this.mActionBarHelper = this.createActionBarHelper();
        this.mActionBarHelper.init();


        if (Build.VERSION.SDK_INT >= 21) {
            this.mAppBarLayout.setElevation(6.6f);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    protected void showBack() {
        if (this.mActionBarHelper != null)
            this.mActionBarHelper.setDisplayHomeAsUpEnabled(true);
    }

    protected void setAppBarLayoutAlpha(float alpha) {
        this.mAppBarLayout.setAlpha(alpha);
    }

    protected void setAppBarLayoutVisibility(boolean visibility) {
        if (visibility) {
            this.mAppBarLayout.setVisibility(View.VISIBLE);
        } else {
            this.mAppBarLayout.setVisibility(View.GONE);
        }
    }

    public ActionBarHelper createActionBarHelper() {
        return new ActionBarHelper();
    }

    public class ActionBarHelper {
        private final ActionBar actionBar;
        public CharSequence drawerTitle;
        public CharSequence title;

        public ActionBarHelper() {
            this.actionBar = getSupportActionBar();
        }

        public void init() {
            if (this.actionBar == null) return;
            this.actionBar.setDisplayHomeAsUpEnabled(true);
            this.actionBar.setDisplayShowHomeEnabled(false);
            this.title = drawerTitle = getTitle();
        }

        public void onDrawerClosed() {
            if (this.actionBar == null) return;
            this.actionBar.setTitle(this.title);
        }

        public void onDrawerOpened() {
            if (actionBar == null) return;
            this.actionBar.setTitle(this.drawerTitle);
        }

        public void setTitle(CharSequence title) {
            this.title = title;
        }

        public void setDrawerTitle(CharSequence drawerTitle) {
            this.drawerTitle = drawerTitle;
        }

        public void setDisplayHomeAsUpEnabled(boolean showHomeAsUp) {
            if (this.actionBar == null) return;
            this.actionBar.setDisplayHomeAsUpEnabled(showHomeAsUp);
        }
    }

    public void setTitle(@StringRes String title) {
        mTitle.setText(title);
    }

    public void setTitle(@StringRes int resId) {
        mTitle.setText(getResources().getString(resId));
    }
}
