package com.example.user.weather.module;

import com.example.user.weather.Repository.MyCityRepositoryImpl;
import com.example.user.weather.Repository.TargetCityRepositoryImpl;
import com.example.user.weather.logic.GeoCodeLogic;
import com.example.user.weather.logic.GeoCodeLogicImpl;
import com.example.user.weather.logic.GeoLogic;
import com.example.user.weather.logic.GeoLogicImpl;
import com.example.user.weather.logic.MyCityLogic;
import com.example.user.weather.logic.MyCityLogicImpl;
import com.example.user.weather.logic.TargetCityLogic;
import com.example.user.weather.logic.TargetCityLogicImpl;
import com.example.user.weather.logic.WeatherLogic;
import com.example.user.weather.logic.WeatherLogicImpl;
import com.example.user.weather.request.GeoApi;
import com.example.user.weather.request.GeoCodingApi;
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
    public GeoCodeLogic provideGeoCodeLogicImpl(GeoCodingApi geoCodingApi) {
        return new GeoCodeLogicImpl(geoCodingApi);
    }

    @Singleton
    @Provides
    public GeoLogic provideGeoLogicImpl(GeoApi geoApi) {
        return new GeoLogicImpl(geoApi);
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