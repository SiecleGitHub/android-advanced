package com.slopestyle.advancedandroid.home;

import com.bluelinelabs.conductor.Controller;
import com.slopestyle.advancedandroid.details.RepoDetailsComponent;
import com.slopestyle.advancedandroid.details.RepoDetailsController;
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
        RepoDetailsComponent.class,
})
public abstract class MainScreenBindingModule {
    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposInjector(TrendingReposComponent.Builder builder);

    @Binds
    @IntoMap
    @ControllerKey(RepoDetailsController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindRepoDetailsInjector(RepoDetailsComponent.Builder builder);
}
