package com.example.user.weather.module;

import android.content.Context;
import com.example.user.weather.Repository.LocationRepository;
import com.example.user.weather.Repository.LocationRepositoryImpl;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DateLayerModule {

    @Singleton
    @Provides
    public LocationRepository provideGeoRepository(Context context) {
        return new LocationRepositoryImpl(context);
    }
}