package com.slopestyle.advancedandroid.base;

import com.slopestyle.advancedandroid.data.RepoServiceModule;
import com.slopestyle.advancedandroid.networking.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        ServiceModule.class,
        RepoServiceModule.class,
        ScreenModule.class,
})
public interface ApplicationComponent {
    void inject(MyApplication myApplication);
}
