package com.slopestyle.advancedandroid.trending;

import com.slopestyle.advancedandroid.R;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import io.reactivex.observers.TestObserver;

public class TrendingReposViewModelTest {

    private TrendingReposViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new TrendingReposViewModel();
    }

    @Test
    public void loading() throws Exception {
        TestObserver<Boolean> loadingObserver = viewModel.loading().test();
        viewModel.loadingUpdated().accept(true);
        viewModel.loadingUpdated().accept(false);

        loadingObserver.assertValues(true, false);
    }

    @Test
    public void error() throws Exception {
        // for error. We need a new test observer
        // and first we will emit a new exception to our on air handler.
        // And after that we will emulate a refresh and say we got a successful API call
        // and we don't actually need a list here so we can just say empty list.
        // Now we can assert that our error observer retrieved the error resource string initially and then negative
        // one after that.
        TestObserver<Integer> errorObserver = viewModel.error().test();
        viewModel.onError().accept(new IOException());
        viewModel.reposUpdated().run();

        errorObserver.assertValues(R.string.api_error_repos, -1);
    }
}