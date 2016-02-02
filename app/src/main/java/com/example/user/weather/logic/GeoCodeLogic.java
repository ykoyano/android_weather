package com.example.user.weather.logic;

import com.example.user.weather.model.GeoCode;
import rx.Observable;

public interface GeoCodeLogic {

    Observable<GeoCode> get(String city);
}
