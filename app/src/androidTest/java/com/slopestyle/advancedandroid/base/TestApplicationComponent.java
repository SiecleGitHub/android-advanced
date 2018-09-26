package com.slopestyle.advancedandroid.base;

import com.slopestyle.advancedandroid.data.RepoRepository;
import com.slopestyle.advancedandroid.data.TestRepoService;
import com.slopestyle.advancedandroid.data.TestRepoServiceModule;
import com.slopestyle.advancedandroid.networking.ServiceModule;
import com.slopestyle.advancedandroid.trending.TrendingReposControllerTest;
import com.slopestyle.advancedandroid.ui.TestNavigationModule;
import com.slopestyle.advancedandroid.ui.TestScreenNavigator;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        TestActivityBindingModule.class,
        TestRepoServiceModule.class,
        ServiceModule.class,
        TestNavigationModule.class,
})
public interface TestApplicationComponent extends ApplicationComponent {
    void inject(TrendingReposControllerTest trendingReposControllerTest);

    TestScreenNavigator screenNavigator();

    TestRepoService repoService();

    RepoRepository repoRepository();
}
