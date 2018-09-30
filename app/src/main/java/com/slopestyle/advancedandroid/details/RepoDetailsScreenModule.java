package com.slopestyle.advancedandroid.details;

import com.slopestyle.advancedandroid.di.ScreenScope;
import com.slopestyle.advancedandroid.lifecycle.ScreenLifecycleTask;
import com.slopestyle.poweradapter.adapter.RecyclerDataSource;
import com.slopestyle.poweradapter.item.ItemRenderer;
import com.slopestyle.poweradapter.item.RecyclerItem;
import com.slopestyle.poweradapter.item.RenderKey;

import java.util.Map;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;

@Module
public abstract class RepoDetailsScreenModule {

    @Binds
    @IntoSet
    abstract ScreenLifecycleTask bindUiManager(RepoDetailsUiManager uiManager);

    @Binds
    @IntoMap
    @RenderKey("Contributor")
    abstract ItemRenderer<? extends RecyclerItem> bindContributorRenderer(ContributorRenderer renderer);

    @Provides
    @ScreenScope
    static RecyclerDataSource provideRecyclerDataSource(Map<String, ItemRenderer<? extends RecyclerItem>> rederers) {
        return new RecyclerDataSource(rederers);
    }
}
