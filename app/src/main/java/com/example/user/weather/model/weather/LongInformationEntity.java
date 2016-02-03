package com.example.user.weather.model.weather;

import com.example.user.weather.model.EntityBase;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LongInformationEntity extends EntityBase{

    @Expose
    private ApiCityEntity city;

    @Expose
    @SerializedName("list")
    private List<LongWeatherEntity> list;

    public void setCity(ApiCityEntity city) {
        this.city = city;
    }

    public void setList(List<LongWeatherEntity> list) {
        this.list = list;
    }

    public ApiCityEntity getCity() {
        return city;
    }

    public List<LongWeatherEntity> getList() {
        return list;
    }
}
