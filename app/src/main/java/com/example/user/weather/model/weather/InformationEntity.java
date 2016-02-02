package com.example.user.weather.model.weather;

import com.example.user.weather.model.EntityBase;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class InformationEntity<T> extends EntityBase {

    @Expose
    private ApiCityEntity city;

    @Expose
    @SerializedName("list")
    private List<WeatherEntity<T>> list;
}