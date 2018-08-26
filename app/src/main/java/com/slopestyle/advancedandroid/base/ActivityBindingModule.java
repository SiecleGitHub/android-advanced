package com.slopestyle.advancedandroid.base;

import android.app.Activity;

import com.slopestyle.advancedandroid.home.MainActivity;
import com.slopestyle.advancedandroid.home.MainActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        MainActivityComponent.class,
})
public abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> provideMainActivityInjetor(MainActivityComponent.Builder builder);
}
