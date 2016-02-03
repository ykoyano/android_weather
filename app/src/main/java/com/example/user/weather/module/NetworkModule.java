package com.example.user.weather.module;

import com.example.user.weather.LocationAdapterFactory;
import com.example.user.weather.request.LocationApi;
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

import javax.inject.Singleton;
import java.util.Date;

@Module
public class NetworkModule {

    private static final String OPEN_WEATHER_MAP_API = "http://api.openweathermap.org";

    private static final String LOCATION_API = "http://geoapi.heartrails.com";

    @Singleton
    @Provides
    public WeatherApi provideWeatherApi() {
        return restAdapter(OPEN_WEATHER_MAP_API)
                .addConverterFactory(GsonConverterFactory.create(gson()))
                .build()
                .create(WeatherApi.class);
    }

    @Singleton
    @Provides
    public LocationApi provideLocationApi() {
        return restAdapter(LOCATION_API).addConverterFactory(GsonConverterFactory.create(locationGson())).build().create(LocationApi.class);
    }

    private Retrofit.Builder restAdapter(final String endpoint) {
        return new Retrofit.Builder().baseUrl(endpoint).addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }

    private Gson gson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).registerTypeAdapter(Date.class, new DateTypeAdapter()).create();
    }

    private Gson locationGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapterFactory(new LocationAdapterFactory()).create();
    }
}