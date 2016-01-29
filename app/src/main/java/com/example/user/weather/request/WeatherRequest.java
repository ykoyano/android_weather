package com.example.user.weather.request;

import com.example.user.weather.model.WeatherModel;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface WeatherRequest {

    @GET("/forecast/webservice/json/v1")
    public Observable<WeatherModel> getWeather(@Query("city") final String city);
}