package com.example.user.weather.model.weather;

import com.example.user.weather.model.EntityBase;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class WeatherEntity<T> extends EntityBase {

    @Expose
    private String dt;
    
    @Expose
    private T main;

    public String getDt() {
        return dt;
    }

    public T getMain() {
        return main;
    }

    public void setMain(T main) {
        this.main = main;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }
}