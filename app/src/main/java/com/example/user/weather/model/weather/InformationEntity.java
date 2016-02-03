package com.example.user.weather.model.weather;

import com.example.user.weather.model.EntityBase;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InformationEntity extends EntityBase {

    @Expose
    private ApiCityEntity city;

    @Expose
    @SerializedName("list")
    private List<WeatherEntity> list;

    public void setCity(ApiCityEntity city) {
        this.city = city;
    }

    public void setList(List<WeatherEntity> list) {
        this.list = list;
    }

    public List<WeatherEntity> getList() {
        return list;
    }

    public ApiCityEntity getCity() {
        return city;
    }
}