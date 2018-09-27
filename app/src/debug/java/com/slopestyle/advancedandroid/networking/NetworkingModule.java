package com.slopestyle.advancedandroid.networking;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Call;
import okhttp3.OkHttpClient;

@Module
public abstract class NetworkingModule {

    @Provides
    @Singleton
    static Call.Factory provideOkHttp(MockInterceptor mockInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(mockInterceptor)
                .build();
    }

    @Provides
    @Named("base_url")
    static String baseUrl() {
        return "https://api.github.com/";
    }
}
