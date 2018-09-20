package com.slopestyle.advancedandroid.data;

import dagger.Binds;

public abstract class TestRepoServiceModule {
    @Binds
    abstract RepoService bindRepoService(TestRepoService repoService);
}
