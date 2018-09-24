package com.slopestyle.advancedandroid.data;

import com.slopestyle.advancedandroid.model.Repo;
import com.slopestyle.advancedandroid.testutils.TestUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Provider;

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class RepoRepositoryTest {

    @Mock Provider<RepoRequester> repoRequesterProvider;
    @Mock RepoRequester repoRequester;

    private RepoRepository repository;
    private TrendingReposResponse trendingReposeResponse    ;
    private Repo rxJavaRepo ;
    private Repo otherRepo;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(repoRequesterProvider.get()).thenReturn(repoRequester);
        trendingReposeResponse = TestUtils.loadJson("mock/get_trending_repos.json", TrendingReposResponse.class);
        when(repoRequester.getTrendingRepos()).thenReturn(Single.just(trendingReposeResponse.repos()));

        rxJavaRepo = trendingReposeResponse.repos().get(0);
        otherRepo = trendingReposeResponse.repos().get(1);

        repository = new RepoRepository(repoRequesterProvider);
    }

    @Test
    public void getTrendingRepos() {
        repository.getTrendingRepos().test().assertValue(trendingReposeResponse.repos());

        List<Repo> modifiedList = new ArrayList<>(trendingReposeResponse.repos());
        modifiedList.remove(0);
        when(repoRequester.getTrendingRepos()).thenReturn(Single.just(modifiedList));

        repository.getTrendingRepos().test().assertValue(trendingReposeResponse.repos());
    }

    @Test
    public void getRepo() {
        repository.getTrendingRepos().subscribe();

        when(repoRequester.getRepo(anyString(), anyString())).thenReturn(Single.just(otherRepo));

        repository.getRepo("ReactiveX", "RxJava").test().assertValue(rxJavaRepo);
        repository.getRepo("NotInCache", "NotInCache").test().assertValue(otherRepo);
    }
}
