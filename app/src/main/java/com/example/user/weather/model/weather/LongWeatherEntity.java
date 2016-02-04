package com.example.user.weather.model.weather;

import com.example.user.weather.model.EntityBase;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LongWeatherEntity extends EntityBase {

    @Expose
    private String dt;

    @Expose
    private TempEntity temp;

    @Expose
    @SerializedName("weather")
    private List<IconEntity> icon;

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public TempEntity getTemp() {
        return temp;
    }

    public void setMain(TempEntity temp) {
        this.temp = temp;
    }

    public List<IconEntity> getIcon() {
        return icon;
    }

    public void setIcon(List<IconEntity> icon) {
        this.icon = icon;
    }
}
