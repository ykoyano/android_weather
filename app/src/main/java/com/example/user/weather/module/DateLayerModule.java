package com.example.user.weather.module;

import com.example.user.weather.Repository.MyCityRepository;
import com.example.user.weather.Repository.MyCityRepositoryImpl;
import com.example.user.weather.Repository.TargetCityRepository;
import com.example.user.weather.Repository.TargetCityRepositoryImpl;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DateLayerModule {

    @Singleton
    @Provides
    public MyCityRepository provideMyCityRepositoryImpl() {
        return new MyCityRepositoryImpl();
    }

    @Singleton
    @Provides
    public TargetCityRepository provideTargetCityRepositoryImpl() {
        return new TargetCityRepositoryImpl();
    }
}