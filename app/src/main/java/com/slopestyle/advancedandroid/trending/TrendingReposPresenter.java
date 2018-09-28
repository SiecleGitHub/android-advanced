package com.slopestyle.advancedandroid.trending;

import android.annotation.SuppressLint;

import com.slopestyle.advancedandroid.data.RepoRepository;
import com.slopestyle.advancedandroid.data.RepoRequester;
import com.slopestyle.advancedandroid.di.ForScreen;
import com.slopestyle.advancedandroid.di.ScreenScope;
import com.slopestyle.advancedandroid.lifecycle.DisposableManager;
import com.slopestyle.advancedandroid.model.Repo;
import com.slopestyle.advancedandroid.ui.ScreenNavigator;

import javax.inject.Inject;

@ScreenScope
class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {

    private final TrendingReposViewModel viewModel;
    private final RepoRepository repoRepository;
    private final ScreenNavigator screenNavigator;
    private final DisposableManager disposableManager;

    @Inject
    TrendingReposPresenter(
            TrendingReposViewModel viewModel,
            RepoRepository repoRepository,
            ScreenNavigator screenNavigator,
            @ForScreen DisposableManager disposableManager) {
        this.viewModel = viewModel;
        this.repoRepository = repoRepository;
        this.screenNavigator = screenNavigator;
        this.disposableManager = disposableManager;
        loadRepos();
    }

    @SuppressLint("CheckResult")
    private void loadRepos() {
        disposableManager.add(repoRepository.getTrendingRepos()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.reposUpdated(), viewModel.onError()));
    }

    @Override
    public void onRepoClicked(Repo repo) {
        screenNavigator.goToRepoDetails(repo.owner().login(), repo.name());
    }
}
