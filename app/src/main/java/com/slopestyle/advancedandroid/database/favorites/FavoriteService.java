package com.slopestyle.advancedandroid.database.favorites;

import android.drm.DrmStore;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.slopestyle.advancedandroid.database.AppDatabase;
import com.slopestyle.advancedandroid.model.Contributor;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@Singleton
public class FavoriteService {

    private final BehaviorRelay<Set<Long>> favoritedContributorIdRelay = BehaviorRelay.createDefault(new HashSet<>());
    private final AppDatabase appDatabase;

    @Inject
    public FavoriteService(AppDatabase database) {
        this.appDatabase = database;
        appDatabase.favoriteContributorDao().getContributors()
                .subscribeOn(Schedulers.io())
                .map(favoriteContributors -> {
                    Set<Long> contributorIds = new HashSet<>();
                    for (FavoriteContributor favoriteContributor : favoriteContributors) {
                        contributorIds.add(favoriteContributor.getId());
                    }
                    return contributorIds;
                })
                .subscribe(favoritedContributorIdRelay,
                        throwable -> {
                            Timber.e(throwable, "Error loading favorited contributors form database.");
                        });
    }

    public Observable<Set<Long>> favoriteContributorIds() {
        return favoritedContributorIdRelay;
    }

    public void toggleFavoriteContributor(Contributor contributor) {
        runDbOp(() -> {
            if (favoritedContributorIdRelay.getValue().contains(contributor.id())) {
                deleteFavoriteContributor(contributor);
            } else {
                addFavoriteContributor(contributor);
            }
        });
    }

    private void addFavoriteContributor(Contributor contributor) {
        appDatabase.favoriteContributorDao().addFavorite(new FavoriteContributor(contributor.id()));
    }

    private void deleteFavoriteContributor(Contributor contributor) {
        appDatabase.favoriteContributorDao().deleteFavorite(new FavoriteContributor(contributor.id()));
    }

    private void runDbOp(Action action) {
        Completable.fromAction(action)
                .subscribeOn(Schedulers.io())
                .subscribe(() -> {
                }, throwable -> Timber.e(throwable, "Error performing database operation."));
    }
}
