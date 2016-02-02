package com.example.user.weather.module;

import com.example.user.weather.GeoAdapterFactory;
import com.example.user.weather.request.GeoApi;
import com.example.user.weather.request.GeoCodingApi;
import com.example.user.weather.request.WeatherApi;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.SimpleXmlConverterFactory;

import javax.inject.Singleton;
import java.util.Date;

@Module
public class NetworkModule {

    private static final String END_POINT = "http://weather.livedoor.com";

    private static final String YAHOO_END_POINT = "http://geo.search.olp.yahooapis.jp";

    private static final String GEO_END_POINT = "http://geoapi.heartrails.com/api/json";


//    @Provides
//    public WeatherRequestImpl provideWeatherRequestImpl(Request request) {
//        return new WeatherRequestImpl(request);
//    }
//
//    @Provides
//    public RequestImpl provideRequestImpl() {
//        return new RequestImpl();
//    }

    @Singleton
    @Provides
    public WeatherApi provideWeatherApi() {
        return restAdapter(END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson()))
                .build()
                .create(WeatherApi.class);
    }

    @Singleton
    @Provides
    public GeoCodingApi provideGeoCodingApi() {
        return restAdapter(YAHOO_END_POINT)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
                .create(GeoCodingApi.class);
    }

    @Singleton
    @Provides
    public GeoApi provideGeoApi() {
        return restAdapter(GEO_END_POINT)
                .addConverterFactory(GsonConverterFactory.create(geoGson()))
                .build()
                .create(GeoApi.class);
    }

    private Retrofit.Builder restAdapter(final String endpoint) {
        return new Retrofit.Builder()
                .baseUrl(endpoint)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }

    private Gson gson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();
    }

    private Gson geoGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapterFactory( new GeoAdapterFactory())
                .create();
    }
}