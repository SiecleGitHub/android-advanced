package com.slopestyle.advancedandroid.trending;

import com.slopestyle.advancedandroid.base.ScreenModule;
import com.slopestyle.advancedandroid.di.ScreenComponent;
import com.slopestyle.advancedandroid.di.ScreenScope;
import com.slopestyle.advancedandroid.networking.ServiceModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        TrendingReposScreenModule.class,
})
public interface TrendingReposComponent extends ScreenComponent<TrendingReposController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<TrendingReposController> {

    }
}
