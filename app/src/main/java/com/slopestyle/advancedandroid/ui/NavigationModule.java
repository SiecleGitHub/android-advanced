package com.slopestyle.advancedandroid.ui;

import com.slopestyle.advancedandroid.di.ActivityScope;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class NavigationModule {

    @Binds
    @ActivityScope
    abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);
}
