package com.slopestyle.advancedandroid.database.favorites;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface FavoriteContributorDao {

    @Query("SELECT * from favoritecontributor")
    Flowable<List<FavoriteContributor>> getContributors();

    @Insert
    void addFavorite(FavoriteContributor contributor);

    @Delete
    void deleteFavorite(FavoriteContributor contributor);
}
