package com.example.user.weather;

import android.support.multidex.MultiDexApplication;
import com.example.user.weather.component.AppComponent;
import com.example.user.weather.component.DaggerAppComponent;
import com.example.user.weather.module.AppModule;

public class AndroidApplication extends MultiDexApplication {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AppComponent getAppComponent() {
        if (component == null) {
            component = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return component;
    }
}
