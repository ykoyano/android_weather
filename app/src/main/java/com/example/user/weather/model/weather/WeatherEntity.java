package com.example.user.weather.model.weather;

import com.example.user.weather.model.EntityBase;
import com.google.gson.annotations.Expose;

public class WeatherEntity extends EntityBase {

    @Expose
    private String dt;
    
    @Expose
    private MainEntity main;

    public String getDt() {
        return dt;
    }

    public MainEntity getMain() {
        return main;
    }

    public void setMain(MainEntity main) {
        this.main = main;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }
}