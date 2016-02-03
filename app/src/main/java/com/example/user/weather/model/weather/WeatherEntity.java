package com.example.user.weather.model.weather;

import com.example.user.weather.model.EntityBase;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherEntity extends EntityBase {

    @Expose
    private String dt;
    
    @Expose
    private MainEntity main;

    @Expose
    @SerializedName("weather")
    private List<IconEntity> icon;

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

    public List<IconEntity> getIcon() {
        return icon;
    }

    public void setIcon(List<IconEntity> icon) {
        this.icon = icon;
    }
}