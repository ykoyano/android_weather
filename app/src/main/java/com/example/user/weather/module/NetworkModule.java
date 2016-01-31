package com.example.user.weather.module;

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

    private static final String END_POINT = "http://weather.livedoor.com";

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
        return restAdapter(END_POINT).build().create(WeatherApi.class);
    }

    private Retrofit.Builder restAdapter(final String endpoint) {
        return new Retrofit.Builder()
                .baseUrl(endpoint)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson()));
    }

    private Gson gson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();
    }
}