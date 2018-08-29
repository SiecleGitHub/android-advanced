package com.slopestyle.advancedandroid.trending;

import com.slopestyle.advancedandroid.data.RepoRequester;
import com.slopestyle.advancedandroid.di.ScreenScope;

import javax.inject.Inject;

@ScreenScope
public class TrendingReposPresenter {

    private TrendingReposViewModel viewModel;
    private RepoRequester repoRequester;

    @Inject
    TrendingReposPresenter(TrendingReposViewModel viewModel, RepoRequester repoRequester) {
        this.viewModel = viewModel;
        this.repoRequester = repoRequester;
        loadRepos();
    }

    private void loadRepos() {
        repoRequester.getTrendingRepos()
                .doOnSubscribe(r -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.reposUpdated(), viewModel.onError());
    }
}
