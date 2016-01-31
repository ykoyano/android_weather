package com.example.user.weather.module;

import android.content.Context;

import com.example.user.weather.AndroidApplication;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final AndroidApplication application;

    public AppModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    public Context provideContext() {
        return application;
    }
}
