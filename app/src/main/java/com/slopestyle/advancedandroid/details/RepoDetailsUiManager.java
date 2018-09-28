package com.slopestyle.advancedandroid.details;

import android.view.View;
import android.support.v7.widget.Toolbar;

import com.slopestyle.advancedandroid.R;
import com.slopestyle.advancedandroid.di.ScreenScope;
import com.slopestyle.advancedandroid.lifecycle.ScreenLifecycleTask;
import com.slopestyle.advancedandroid.ui.ScreenNavigator;
import com.slopestyle.advancedandroid.util.ButterknifeUtils;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@ScreenScope
public class RepoDetailsUiManager extends ScreenLifecycleTask{

    // java.lang.ClassCastException: android.support.v7.widget.Toolbar cannot be cast to android.widget.Toolbar
    // private Toolbar toolbar;

    @BindView(R.id.toolbar) Toolbar toolbar;

    private Unbinder unbinder;

    private final String repoName;
    private final ScreenNavigator screenNavigator;

    @Inject
    RepoDetailsUiManager(@Named("repo_name") String repoName, ScreenNavigator screenNavigator) {

        this.repoName = repoName;
        this.screenNavigator = screenNavigator;
    }

    @Override
    public void onEnterScope(View view) {
        //toolbar = view.findViewById(R.id.toolbar);

        unbinder = ButterKnife.bind(this, view);
        toolbar.setTitle(repoName);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        // pop the screen
        toolbar.setNavigationOnClickListener(v -> screenNavigator.pop());
    }

    @Override
    public void onExitScope() {
        toolbar.setNavigationOnClickListener(null);
        ButterknifeUtils.unbind(unbinder);
    }
}
