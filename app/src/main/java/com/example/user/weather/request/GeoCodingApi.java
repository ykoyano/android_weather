package com.example.user.weather.request;

import com.example.user.weather.model.GeoCode;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface GeoCodingApi {

    @GET("/OpenLocalPlatform/V1/geoCoder")
    Observable<GeoCode> get(@Query("appid") final String appId, @Query("city") final String city);
}
