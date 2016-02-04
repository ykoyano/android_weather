package com.example.user.weather.model.weather;

import com.example.user.weather.model.EntityBase;
import com.google.gson.annotations.Expose;

public class LongWeatherEntity extends EntityBase {

    @Expose
    private String dt;

    @Expose
    private TempEntity temp;

    public String getDt() {
        return dt;
    }

    public TempEntity getTemp() {
        return temp;
    }

    public void setMain(TempEntity temp) {
        this.temp = temp;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }
}
