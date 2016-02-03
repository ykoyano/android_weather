package com.example.user.weather.request;

import com.example.user.weather.model.Location;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

import java.util.List;

public interface LocationApi {

    @GET("/api/json")
    Observable<List<String>> getAreas(@Query("method") final String method);

    @GET("/api/json")
    Observable<List<String>> getPrefectures(@Query("method") final String method, @Query("area") final String area);

    @GET("/api/json")
    Observable<List<Location>> getCities(@Query("method") final String method, @Query("area") final String area, @Query("prefecture") final String prefecture);

    @GET("/api/json")
    Observable<List<Location>> getAddressByCoordinate(@Query("method") final String method, @Query("x") final double lon, @Query("y") final double lat);

    @GET("/api/json")
    Observable<List<Location>> getAddressByKeyword(@Query("method") final String method, @Query("matching") final String matching, @Query("keyword") final String keyword);
}
