package com.example.user.weather.model;

import android.databinding.Bindable;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GeoEntity extends EntityBase {

    @Expose
    private String city;

    @Expose
    private String town;

    @Expose
    private double x;

    @Expose
    private double y;

//    @Bindable
    public String getCity() {
        return city;
    }

//    @Bindable
    public String getTown() {
        return town;
    }

//    @Bindable
    public double getX() {
        return x;
    }

//    @Bindable
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setCity(String city) {
        this.city = city;
    }


}