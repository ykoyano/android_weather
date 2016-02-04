package com.example.user.weather.request;

import com.example.user.weather.model.weather.InformationEntity;
import com.example.user.weather.model.weather.LongInformationEntity;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import rx.Observable;

public interface WeatherApi {

    @Headers({
            "Content-type: application/json" })

    @GET("/data/2.5/forecast")
    Observable<InformationEntity> getWeather(@Query("lon") final double lon, @Query("lat") final double lat, @Query("appid") final String appId);

    @GET("/data/2.5/forecast/daily")
    Observable<LongInformationEntity> getWeatherWeek(@Query("lon") final double lon, @Query("lat") final double lat, @Query("appid") final String appId);
}