package com.slopestyle.advancedandroid.details;

import com.slopestyle.advancedandroid.R;
import com.slopestyle.advancedandroid.model.Contributor;
import com.slopestyle.advancedandroid.model.Repo;
import com.slopestyle.advancedandroid.testutils.TestUtils;
import com.squareup.moshi.Types;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class RepoDetailsViewModelTest {

    private RepoDetailsViewModel viewModel;

    private Repo repo = TestUtils.loadJson("mock/repos/get_repo.json", Repo.class);
    private List<Contributor> contributors = TestUtils.loadJson("mock/repos/contributors/get_contributors.json",
            Types.newParameterizedType(List.class, Contributor.class));

    @Before
    public void setUp() {
        viewModel = new RepoDetailsViewModel();
    }

    @Test
    public void details() throws Exception {
        viewModel.processRepo().accept(repo);

        viewModel.details().test().assertValue(
                RepoDetailState.builder()
                        .loading(false)
                        .name("RxJava")
                        .description("RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.")
                        .createdDate("Jan 08, 2013")
                        .updatedDate("Okt 06, 2017")
                        .build()
        );
    }

    @Test
    public void detailsError() throws Exception {
        viewModel.detailsError().accept(new IOException());

        viewModel.details().test().assertValue(
            RepoDetailState.builder()
                    .loading(false)
                    .errorRes(R.string.api_error_single_repo)
                    .build()
        );
    }

    @Test
    public void contributors() throws Exception {
        viewModel.processContributors().accept(contributors);

        viewModel.contributors().test().assertValue(
                ContributorState.builder()
                        .loading(false)
                        .contributors(contributors)
                        .build()
        );
    }

    @Test
    public void contributorsError() throws Exception {
        viewModel.contributorsError().accept(new IOException());

        viewModel.contributors().test().assertValue(
                ContributorState.builder()
                        .loading(false)
                        .errorRes(R.string.api_error_contributors)
                        .build()
        );
    }
}