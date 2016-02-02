package com.example.user.weather.request;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import rx.Observable;

import java.util.List;

public interface GeoApi {

    @Headers({
            "Content-type: application/json" })

    @GET
    Observable<List<String>> getCities(@Query("method") final String method, @Query("prefecture") final String prefecture);

    @GET
    Observable<List<String>> getAddressByCoordinate(@Query("method") final String method, @Query("x") final double lon, @Query("y") final double lat);

    @GET
    Observable<List<String>> getAddressByKeyword(@Query("method") final String method, @Query("matching") final String matching, @Query("keyword") final String keyword);
}
