package com.example.user.weather.model.weather;

import com.example.user.weather.model.EntityBase;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class TempEntity extends EntityBase {

    @Expose
    private double day;

    @Expose
    private double eve;

    @Expose
    private double night;

    @Expose
    private double max;

    @Expose
    private double min;

    @Expose
    @SerializedName("weather")
    private List<IconEntity> icon;

    public double getDay() {
        return day;
    }

    public double getEve() {
        return eve;
    }

    public double getNight() {
        return night;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public void setDay(double day) {
        this.day = day;
    }

    public void setEve(double eve) {
        this.eve = eve;
    }

    public void setNight(double night) {
        this.night = night;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public List<IconEntity> getIcon() {
        return icon;
    }

    public void setIcon(List<IconEntity> icon) {
        this.icon = icon;
    }
}
