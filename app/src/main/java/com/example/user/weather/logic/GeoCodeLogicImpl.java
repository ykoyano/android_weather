package com.example.user.weather.logic;

import com.example.user.weather.model.GeoCode;
import com.example.user.weather.request.GeoCodingApi;
import rx.Observable;

import javax.inject.Inject;

public class GeoCodeLogicImpl implements GeoCodeLogic {

    private GeoCodingApi geoCodingApi;

    private static final String APP_ID = "dj0zaiZpPUFPVjNZSG81NzdBNCZzPWNvbnN1bWVyc2VjcmV0Jng9NmQ-";

    @Inject
    public GeoCodeLogicImpl(GeoCodingApi geoCodingApi) {
        this.geoCodingApi = geoCodingApi;
    }

    @Override
    public Observable<GeoCode> get(String city) {
        return geoCodingApi.get(APP_ID, city);
    }

}
