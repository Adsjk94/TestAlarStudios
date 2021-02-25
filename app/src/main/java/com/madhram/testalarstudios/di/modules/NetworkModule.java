package com.madhram.testalarstudios.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.madhram.testalarstudios.BuildConfig;
import com.madhram.testalarstudios.api.AlarstudiosApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static io.reactivex.schedulers.Schedulers.io;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().generateNonExecutableJson().setLenient().create();
    }

    @Provides
    @Singleton
    OkHttpClient provideClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BODY);
        logging.level(HttpLoggingInterceptor.Level.HEADERS);

        OkHttpClient.Builder mBuilder = new OkHttpClient().newBuilder();
        mBuilder.addInterceptor(logging);
        mBuilder.retryOnConnectionFailure(true);
        mBuilder.connectTimeout(5, TimeUnit.SECONDS);
        return mBuilder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(io()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    AlarstudiosApi provideApiService(Retrofit retrofit) {
        return retrofit.create(AlarstudiosApi.class);
    }
}