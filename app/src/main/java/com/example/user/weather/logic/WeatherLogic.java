package com.example.user.weather.logic;

import com.example.user.weather.model.weather.InformationEntity;
import com.example.user.weather.model.weather.LongInformationEntity;
import rx.Observable;

public interface WeatherLogic {

    Observable<InformationEntity> getDailyWeather(double lon, double lat);

    Observable<LongInformationEntity> getWeekWeather(double lon, double lat);
}