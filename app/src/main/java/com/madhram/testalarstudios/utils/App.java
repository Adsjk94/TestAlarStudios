package com.madhram.testalarstudios.utils;

import android.app.Application;

import com.madhram.testalarstudios.di.components.AppComponent;
import com.madhram.testalarstudios.di.components.DaggerAppComponent;

import lombok.Getter;

public class App extends Application {

    @Getter
    private static App INSTANCE;

    @Getter
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;

        if (appComponent == null){
            appComponent = DaggerAppComponent.builder().build();
        }
    }
}