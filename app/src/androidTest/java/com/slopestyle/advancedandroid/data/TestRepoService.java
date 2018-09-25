package com.slopestyle.advancedandroid.data;

import com.slopestyle.advancedandroid.model.Contributor;
import com.slopestyle.advancedandroid.model.Repo;
import com.slopestyle.advancedandroid.test.TestUtils;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class TestRepoService implements RepoService {

    private final TestUtils testUtils;
    private boolean sendError;

    @Inject
    TestRepoService(TestUtils testUtils) {
        this.testUtils = testUtils;
    }

    @Override
    public Single<TrendingReposResponse> getTrendingRepos() {
        if(!sendError) {
            TrendingReposResponse response = testUtils.loadJson(
                    "mock/get_trending_repos.json",
                    TrendingReposResponse.class);
            return Single.just(response);
        }

        return Single.error(new IOException());
    }

    @Override
    public Single<Repo> getRepo(String repoOwner, String repoName) {
        return null;
    }

    @Override
    public Single<List<Contributor>> getContributors(String url) {
        return null;
    }

    public void setSendError(boolean sendError) {
        this.sendError = sendError;
    }
}
