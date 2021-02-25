package com.madhram.testalarstudios.di.components;

import com.madhram.testalarstudios.activities.MainActivity;
import com.madhram.testalarstudios.activities.TableActivity;
import com.madhram.testalarstudios.di.modules.AppModule;
import com.madhram.testalarstudios.di.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(MainActivity injector);

    void inject(TableActivity injector);

}