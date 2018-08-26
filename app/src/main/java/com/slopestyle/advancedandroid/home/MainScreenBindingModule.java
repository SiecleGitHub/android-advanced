package com.slopestyle.advancedandroid.home;

import com.bluelinelabs.conductor.Controller;
import com.slopestyle.advancedandroid.di.ControllerKey;
import com.slopestyle.advancedandroid.trending.TrendingReposComponent;
import com.slopestyle.advancedandroid.trending.TrendingReposController;

import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        TrendingReposComponent.class,
})
public abstract class MainScreenBindingModule {
    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposInjector(TrendingReposComponent.Builder builder);
}
