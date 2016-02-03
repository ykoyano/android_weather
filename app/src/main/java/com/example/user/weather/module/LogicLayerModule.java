package com.example.user.weather.module;

import com.example.user.weather.Repository.LocationRepository;
import com.example.user.weather.logic.LocationLogic;
import com.example.user.weather.logic.LocationLogicImpl;
import com.example.user.weather.logic.WeatherLogic;
import com.example.user.weather.logic.WeatherLogicImpl;
import com.example.user.weather.request.LocationApi;
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
    public LocationLogic provideGeoLogic(LocationApi locationApi, LocationRepository repository) {
        return new LocationLogicImpl(locationApi, repository);
    }
}