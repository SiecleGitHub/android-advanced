package com.slopestyle.advancedandroid.model;

import com.google.auto.value.AutoValue;
import com.slopestyle.poweradapter.item.RecyclerItem;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Contributor implements RecyclerItem {
    public abstract long id();

    public abstract String login();

    @Json(name = "avatar_url")
    public abstract String avatarUrl();

    @Override
    public long getId() {
        return id();
    }

    @Override
    public String renderKey() {
        return "Contributor";
    }

    public static JsonAdapter<Contributor> jsonAdapter(Moshi moshi) {
        return new AutoValue_Contributor.MoshiJsonAdapter(moshi);
    }
}
