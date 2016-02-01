package com.example.user.weather.logic;

import com.example.user.weather.Repository.TargetCityRepository;

import javax.inject.Inject;

public class TargetCityLogicImpl implements TargetCityLogic{

    private TargetCityRepository repository;

    @Inject
    public TargetCityLogicImpl(TargetCityRepository repository){
        this.repository = repository;
    }
}
