package com.example.user.weather.request;

import com.example.user.weather.model.WeatherModel;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import rx.Observable;

public interface WeatherApi {

    @Headers({
            "Content-type: application/json"
    })

    @GET("/forecast/webservice/json/v1")
    Observable<WeatherModel> getWeather(@Query("city") final String city);
}