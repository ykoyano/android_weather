package com.example.user.weather.logic;

import com.example.user.weather.Repository.MyCityRepository;

import javax.inject.Inject;

public class MyCityLogicImpl implements MyCityLogic {

    private MyCityRepository myCityRepository;

    @Inject
    public MyCityLogicImpl(MyCityRepository myCityRepository) {
        this.myCityRepository = myCityRepository;
    }
}
