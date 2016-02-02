package com.example.user.weather.request;

import com.example.user.weather.model.InformationEntity;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import rx.Observable;

public interface WeatherApi {

    @Headers({
            "Content-type: application/json" })

    @GET("/data/2.5/forecast")
    Observable<InformationEntity> getWeather(@Query("lat") final double lat, @Query("lon") final double lon, @Query("appid") final String appId);

    @GET("/data/2.5/forecast/daily")
    Observable<InformationEntity> getWeatherWeek(@Query("lat") final double lat, @Query("lon") final double lon);
}