package com.slopestyle.advancedandroid.di;

import com.slopestyle.advancedandroid.lifecycle.DisposableManager;

import dagger.android.AndroidInjector;

public interface ScreenComponent<T> extends AndroidInjector<T> {

    @ForScreen
    DisposableManager disposableManager();
}
