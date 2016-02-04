package com.example.user.weather.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date unixTimeToDate(String unixTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Date nowTime = new Date(Integer.parseInt(unixTime) * 1000L);
        try {
            return sdf.parse(sdf.format(nowTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
}