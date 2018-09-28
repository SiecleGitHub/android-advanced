package com.slopestyle.advancedandroid.base;

import com.slopestyle.advancedandroid.di.ForScreen;
import com.slopestyle.advancedandroid.di.ScreenScope;
import com.slopestyle.advancedandroid.lifecycle.DisposableManager;
import com.slopestyle.advancedandroid.lifecycle.ScreenLifecycleTask;

import java.util.Set;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.Multibinds;

@Module
public abstract class ScreenModule {

    @Provides
    @ScreenScope
    @ForScreen
    static DisposableManager provideDisposableManager() {
        return new DisposableManager();
    }

    @Multibinds
    abstract Set<ScreenLifecycleTask> screenLifecycleTasks();
}
