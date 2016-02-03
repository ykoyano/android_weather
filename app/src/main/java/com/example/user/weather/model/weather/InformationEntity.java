package com.example.user.weather.model.weather;

import android.databinding.Bindable;
import com.example.user.weather.model.EntityBase;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class InformationEntity<T> extends EntityBase {

    @Expose
    private ApiCityEntity city;

    @Expose
    @SerializedName("list")
    private List<WeatherEntity<T>> list;

    public void setCity(ApiCityEntity city) {
        this.city = city;
    }

    public void setList(List<WeatherEntity<T>> list) {
        this.list = list;
    }

    public List<WeatherEntity<T>> getList() {
        return list;
    }

    public ApiCityEntity getCity() {
        return city;
    }
}