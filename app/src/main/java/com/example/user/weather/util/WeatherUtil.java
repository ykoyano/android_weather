package com.example.user.weather.util;

import com.example.user.weather.R;

public class WeatherUtil {

    private static final String CELSIUS =  "℃";
    private static final double KELVIN = 273.15f;

    public static String totringTemperature(double temp){
        return String.valueOf((int) temp - KELVIN + CELSIUS);
    }

    public static int getIconResource(String icon) {
        switch (icon) {
        case "01d":
            return R.drawable.sun;
        case "02d":
            return R.drawable.herf;
        case "03d":
        case "03n":
            return R.drawable.cloud;
        case "04d":
        case "04n":
            return R.drawable.double_cloud;
        case "01n":
            return R.drawable.moon;
        case "02n":
            return R.drawable.moon_and_cloud;
        case "09d":
        case "09n":
            return R.drawable.rain;
        case "10d":
        case "10n":
            return R.drawable.rain_and_moon;
        case "11d":
        case "11n":
            return R.drawable.squall;
        case "13d":
        case "13n":
            return R.drawable.snow;
        default:
            throw new IllegalArgumentException("InvalidValue=" + icon);}
    }
}


