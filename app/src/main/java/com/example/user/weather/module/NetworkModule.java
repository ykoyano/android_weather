package com.example.user.weather.module;

import com.example.user.weather.GeoAdapterFactory;
import com.example.user.weather.request.GeoApi;
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

    private static final String OPEN_WEATHER_MAP_END_POINT = "http://api.openweathermap.org";

    private static final String GEO_END_POINT = "http://geoapi.heartrails.com";

    @Singleton
    @Provides
    public WeatherApi provideWeatherApi() {
        return restAdapter(OPEN_WEATHER_MAP_END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson()))
                .build()
                .create(WeatherApi.class);
    }

    @Singleton
    @Provides
    public GeoApi provideGeoApi() {
        return restAdapter(GEO_END_POINT).addConverterFactory(GsonConverterFactory.create(geoGson())).build().create(GeoApi.class);
    }

    private Retrofit.Builder restAdapter(final String endpoint) {
        return new Retrofit.Builder().baseUrl(endpoint).addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }

    private Gson gson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).registerTypeAdapter(Date.class, new DateTypeAdapter()).create();
    }

    private Gson geoGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapterFactory(new GeoAdapterFactory()).create();
    }
}