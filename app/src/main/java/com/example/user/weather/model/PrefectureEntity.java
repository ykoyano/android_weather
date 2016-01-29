package com.example.user.weather.model;

import android.databinding.Bindable;
import lombok.AllArgsConstructor;


import java.util.List;

@AllArgsConstructor
public class PrefectureEntity {

    private String title;

    private List<CityEntity> cities;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCities(List<CityEntity> cities) {
        this.cities = cities;
    }

    public String getTitle() {
        return title;
    }

    public List<CityEntity> getCities() {
        return cities;
    }
}
