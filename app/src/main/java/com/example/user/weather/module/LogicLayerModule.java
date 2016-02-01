package com.example.user.weather.module;

import com.example.user.weather.Repository.MyCityRepositoryImpl;
import com.example.user.weather.Repository.TargetCityRepositoryImpl;
import com.example.user.weather.logic.MyCityLogic;
import com.example.user.weather.logic.MyCityLogicImpl;
import com.example.user.weather.logic.TargetCityLogic;
import com.example.user.weather.logic.TargetCityLogicImpl;
import com.example.user.weather.logic.WeatherLogic;
import com.example.user.weather.logic.WeatherLogicImpl;
import com.example.user.weather.request.WeatherApi;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class LogicLayerModule {

    @Singleton
    @Provides
    public WeatherLogic provideWeatherLogic(WeatherApi weatherApi) {
        return new WeatherLogicImpl(weatherApi);
    }

    @Singleton
    @Provides
    public MyCityLogic provideMyCityLogicImpl(MyCityRepositoryImpl repository) {
        return new MyCityLogicImpl(repository);
    }

    @Singleton
    @Provides
    public TargetCityLogic provideTargetCityLogicImpl(TargetCityRepositoryImpl repository) {
        return new TargetCityLogicImpl(repository);
    }
}