package com.slopestyle.advancedandroid.test;

import android.content.Intent;

import com.bluelinelabs.conductor.Controller;
import com.slopestyle.advancedandroid.data.RepoRepository;
import com.slopestyle.advancedandroid.data.TestRepoService;
import com.slopestyle.advancedandroid.home.MainActivity;
import com.slopestyle.advancedandroid.ui.TestScreenNavigator;

import org.junit.Rule;

public abstract class ControllerTest {
    @Rule public ControllerTestRule<MainActivity> testRule = new ControllerTestRule<>(MainActivity.class);

    protected TestScreenNavigator screenNavigator = testRule.screenNavigator;
    protected TestRepoService repoService = testRule.repoService;
    protected RepoRepository repoRepository = testRule.repoRepository;

    public ControllerTest() {
        screenNavigator.overrideInitialController(controllerToLaunch());
    }

    protected abstract Controller controllerToLaunch();

    protected void launch() {
        launch(null);
    }

    protected void launch(Intent intent) {
        testRule.launchActivity(intent);
    }
}
