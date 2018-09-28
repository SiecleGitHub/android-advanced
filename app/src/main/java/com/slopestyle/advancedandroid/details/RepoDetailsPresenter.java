package com.slopestyle.advancedandroid.details;

import android.annotation.SuppressLint;

import com.slopestyle.advancedandroid.data.RepoRepository;
import com.slopestyle.advancedandroid.di.ForScreen;
import com.slopestyle.advancedandroid.di.ScreenScope;
import com.slopestyle.advancedandroid.lifecycle.DisposableManager;

import javax.inject.Inject;
import javax.inject.Named;

import timber.log.Timber;

@ScreenScope
public class RepoDetailsPresenter {

    @SuppressLint("CheckResult")
    @Inject
    RepoDetailsPresenter(
            @Named("repo_owner") String repoOwner,
            @Named("repo_name") String repoName,
            RepoRepository repoRepository,
            RepoDetailsViewModel viewModel,
            @ForScreen DisposableManager disposableManager) {
                disposableManager.add(
                    repoRepository.getRepo(repoOwner, repoName)
                    .doOnSuccess(viewModel.processRepo())
                    .doOnError(viewModel.detailsError())
                    .flatMap(repo -> repoRepository.getContributors(repo.contributorsUrl())
                    .doOnError(viewModel.contributorsError()))
                    .subscribe(viewModel.processContributors(), throwable -> {
                            // We handle logging in the view model
                            Timber.e(throwable, "Error loading repo");
                    }));
            }
}
