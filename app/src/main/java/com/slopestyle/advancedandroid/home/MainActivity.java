package com.slopestyle.advancedandroid.home;

import com.bluelinelabs.conductor.Controller;
import com.slopestyle.advancedandroid.R;
import com.slopestyle.advancedandroid.base.BaseActivity;
import com.slopestyle.advancedandroid.trending.TrendingReposController;

public class MainActivity extends BaseActivity {
    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public Controller initialScreen() {
        return new TrendingReposController();
    }
}
