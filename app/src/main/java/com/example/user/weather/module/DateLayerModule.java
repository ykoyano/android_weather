package com.example.user.weather.module;

import android.content.Context;
import com.example.user.weather.Repository.GeoRepository;
import com.example.user.weather.Repository.GeoRepositoryImpl;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DateLayerModule {

    @Singleton
    @Provides
    public GeoRepository provideGeoRepository(Context context) {
        return new GeoRepositoryImpl(context);
    }
}