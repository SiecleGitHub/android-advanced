package com.slopestyle.advancedandroid.test;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import com.slopestyle.advancedandroid.base.TestApplication;
import com.slopestyle.advancedandroid.data.RepoRepository;
import com.slopestyle.advancedandroid.data.TestRepoService;
import com.slopestyle.advancedandroid.ui.TestScreenNavigator;

public class ControllerTestRule<T extends Activity> extends ActivityTestRule<T> {

    public final TestScreenNavigator screenNavigator;
    public final TestRepoService repoService;
    public final RepoRepository repoRepository;

    public ControllerTestRule(Class<T> activityClass) {
        super(activityClass, true, false);
        screenNavigator = TestApplication.getComponent().screenNavigator();
        repoService = TestApplication.getComponent().repoService();
        repoRepository = TestApplication.getComponent().repoRepository();
    }

    public void clearState() {
        repoService.clearErrorFlags();
        repoService.clearHoldFlags();
        repoRepository.clearCache();
    }
}
