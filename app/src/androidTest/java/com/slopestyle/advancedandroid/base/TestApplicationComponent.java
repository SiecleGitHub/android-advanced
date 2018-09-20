package com.slopestyle.advancedandroid.base;

import com.slopestyle.advancedandroid.data.TestRepoServiceModule;
import com.slopestyle.advancedandroid.networking.ServiceModule;
import com.slopestyle.advancedandroid.ui.NavigationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        TestActivityBindingModule.class,
        TestRepoServiceModule.class,
        ServiceModule.class,
        NavigationModule.class,
})
public interface TestApplicationComponent extends ApplicationComponent {
}
