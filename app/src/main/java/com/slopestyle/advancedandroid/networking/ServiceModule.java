package com.slopestyle.advancedandroid.networking;

import com.slopestyle.advancedandroid.model.AdapterFactory;
import com.slopestyle.advancedandroid.model.ZonedDateTimeAdapter;
import com.squareup.moshi.Moshi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module(includes = NetworkingModule.class)
public abstract class ServiceModule {

    @Singleton
    @Provides
    static Moshi provideMoshi() {
        return new Moshi.Builder()
                .add(AdapterFactory.create())
                .add(new ZonedDateTimeAdapter())
                .build();
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofit(Moshi moshi, Call.Factory callFactory, @Named("base_url") String baseUrl) {
        return new Retrofit.Builder()
                .callFactory(callFactory)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }
}
