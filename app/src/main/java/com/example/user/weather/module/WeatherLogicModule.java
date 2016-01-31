package com.example.user.weather.module;

import com.example.user.weather.logic.WeatherLogic;
import com.example.user.weather.logic.WeatherLogicImpl;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class WeatherLogicModule {

    @Singleton
    @Provides
    public WeatherLogic provideWeatherLogic() {
        return new WeatherLogicImpl();
    }
}
