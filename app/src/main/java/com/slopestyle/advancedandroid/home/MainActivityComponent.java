package com.slopestyle.advancedandroid.home;

import com.slopestyle.advancedandroid.di.ActivityScope;
import com.slopestyle.advancedandroid.ui.ActivityViewInterceptorModule;
import com.slopestyle.advancedandroid.ui.NavigationModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = {
        MainScreenBindingModule.class,
        NavigationModule.class,
        ActivityViewInterceptorModule.class,
})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {
        @Override
        public void seedInstance(MainActivity instance) {

        }
    }
}
