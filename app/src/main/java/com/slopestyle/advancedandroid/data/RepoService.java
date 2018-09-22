package com.slopestyle.advancedandroid.data;

import com.slopestyle.advancedandroid.model.Repo;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoService {
    /* get request for repos */
    @GET("search/repositories?q=language:java&order=desc&sort=stars")
    Single<TrendingReposResponse> getTrendingRepos();

    @GET("repo/{owner}/{name}")
    Single<Repo> getRepo(@Path("owner") String repoOwner, @Path("name") String repoName);
}
