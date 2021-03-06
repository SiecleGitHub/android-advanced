package com.slopestyle.advancedandroid.trending;

import com.slopestyle.advancedandroid.data.RepoRepository;
import com.slopestyle.advancedandroid.data.TrendingReposResponse;
import com.slopestyle.advancedandroid.lifecycle.DisposableManager;
import com.slopestyle.advancedandroid.model.Repo;
import com.slopestyle.advancedandroid.testutils.TestUtils;
import com.slopestyle.advancedandroid.ui.ScreenNavigator;
import com.slopestyle.poweradapter.adapter.RecyclerDataSource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class TrendingReposPresenterTest {

    static {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    @Mock private RepoRepository repoRepository;
    @Mock private TrendingReposViewModel viewModel;
    @Mock private Consumer<Throwable> onErrorConsumer;
    @Mock private Consumer<Boolean> loadingConsumer;
    @Mock private ScreenNavigator screenNavigator;
    @Mock private RecyclerDataSource dataSource;

    private TrendingReposPresenter presenter;

    @Before

    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(viewModel.loadingUpdated()).thenReturn(loadingConsumer);
        when(viewModel.onError()).thenReturn(onErrorConsumer);
        when(viewModel.reposUpdated()).thenReturn(() -> {});
    }

    @Test
    public void reposLoaded() {
        List<Repo> repos = setUpSuccess();
        initializePresenter();

        verify(repoRepository).getTrendingRepos();
        verify(dataSource).setData(repos);
        verifyZeroInteractions(onErrorConsumer);
    }

    @Test
    public void reposLoadedError() throws Exception {
        Throwable error = setUpError();
        initializePresenter();

        verify(onErrorConsumer).accept(error);
        verifyZeroInteractions(dataSource);
    }

    @Test
    public void loadingSuccess() throws Exception {
        setUpSuccess();
        initializePresenter();

        InOrder inOrder = Mockito.inOrder(loadingConsumer);
        inOrder.verify(loadingConsumer).accept(true);
        inOrder.verify(loadingConsumer).accept(false);
    }

    @Test
    public void loadingError() throws Exception {
        //noinspection ThrowableNotThrown
        setUpError();
        initializePresenter();

        InOrder inOrder = Mockito.inOrder(loadingConsumer);
        inOrder.verify(loadingConsumer).accept(true);
        inOrder.verify(loadingConsumer).accept(false);
    }

    @Test
    public void onRepoClicked() {
        Repo repo = TestUtils.loadJson("mock/repos/get_repo.json", Repo.class);
        setUpSuccess();
        initializePresenter();
        presenter.onRepoClicked(repo);

        verify(screenNavigator).goToRepoDetails(repo.owner().login(), repo.name());
    }

    private List<Repo> setUpSuccess() {
        TrendingReposResponse response = TestUtils.loadJson("mock/search/get_trending_repos.json", TrendingReposResponse.class);
        List<Repo> repos = response.repos();

        when(repoRepository.getTrendingRepos()).thenReturn(Single.just(repos));
        return repos;
    }

    private Throwable setUpError() {
        Throwable error = new IOException();
        when(repoRepository.getTrendingRepos()).thenReturn(Single.error(error));

        return error;
    }

    private void initializePresenter() {
        presenter = new TrendingReposPresenter(viewModel, repoRepository, screenNavigator,
                Mockito.mock(DisposableManager.class), dataSource);
    }}