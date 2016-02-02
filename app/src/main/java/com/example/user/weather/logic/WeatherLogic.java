package com.example.user.weather.logic;

import com.example.user.weather.model.InformationEntity;
import rx.Observable;

public interface WeatherLogic {

    Observable<InformationEntity> getWeather(double lat, double lon);
}
