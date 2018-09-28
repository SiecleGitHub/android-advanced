package com.slopestyle.advancedandroid.ui;

public interface ScreenNavigator {

    boolean pop();

    void goToRepoDetails(String repoOwner, String repoName);
}
