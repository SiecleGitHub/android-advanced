package com.slopestyle.advancedandroid.trending;

import android.view.View;
import android.support.v7.widget.Toolbar;

import com.slopestyle.advancedandroid.R;
import com.slopestyle.advancedandroid.di.ScreenScope;
import com.slopestyle.advancedandroid.lifecycle.ScreenLifecycleTask;
import com.slopestyle.advancedandroid.util.ButterknifeUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@ScreenScope
public class TrendingReposUiManager extends ScreenLifecycleTask {

    // java.lang.ClassCastException: android.support.v7.widget.Toolbar cannot be cast to android.widget.Toolbar
    // private Toolbar toolbar;

    @BindView(R.id.toolbar) Toolbar toolbar;

    private Unbinder unbinder;

    @Inject
    TrendingReposUiManager() {

    }

    @Override
    public void onEnterScope(View view) {
        //toolbar = view.findViewById(R.id.toolbar);

        unbinder = ButterKnife.bind(this, view);
        toolbar.setTitle(R.string.screen_title_trending);
    }

    @Override
    public void onExitScope() {
        ButterknifeUtils.unbind(unbinder);
    }
}
