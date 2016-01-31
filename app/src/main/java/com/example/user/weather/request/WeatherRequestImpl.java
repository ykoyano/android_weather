//package com.example.user.weather.request;
//
//import com.example.user.weather.model.WeatherModel;
//import rx.Observable;
//
//import javax.inject.Inject;
//
//public class WeatherRequestImpl implements WeatherRequest {
//
//    private Request request;
//
//    @Inject
//    public WeatherRequestImpl(Request request) {
//        this.request = request;
//    }
//
//    @Override
//    public Observable<WeatherModel> get(String city) {
//         return request.connection().create(WeatherApi.class).getWeather(city);
//    }
//}