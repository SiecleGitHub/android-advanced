package com.slopestyle.advancedandroid.details;

import com.slopestyle.advancedandroid.data.RepoRepository;
import com.slopestyle.advancedandroid.di.ScreenScope;

import javax.inject.Inject;
import javax.inject.Named;

@ScreenScope
public class RepoDetailsPresenter {

    @Inject
    RepoDetailsPresenter(
            @Named("repo_owner") String repoOwner,
            @Named("repo_name") String repoName,
            RepoRepository repoRepository,
            RepoDetailsViewModel viewModel) {
        repoRepository.getRepo(repoOwner, repoName)
                .doOnSuccess(viewModel.processRepo())
                .doOnError(viewModel.detailsError())
                .flatMap(repo -> repoRepository.getContributors(repo.contributorsUrl())
                .doOnError(viewModel.contributorsError()))
                .subscribe(viewModel.processContributors(), throwable -> {
                    // We handle logging in the view model
                });
    }
}
