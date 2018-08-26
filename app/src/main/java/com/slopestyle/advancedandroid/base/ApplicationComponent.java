package com.slopestyle.advancedandroid.base;

import com.slopestyle.advancedandroid.di.ActivityScope;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
})
public interface ApplicationComponent {
    void inject(MyApplication myApplication);
}