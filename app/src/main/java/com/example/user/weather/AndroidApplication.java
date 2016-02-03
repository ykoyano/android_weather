package com.example.user.weather;

import android.support.multidex.MultiDexApplication;
import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.example.user.weather.component.AppComponent;
import com.example.user.weather.component.DaggerAppComponent;
import com.example.user.weather.module.AppModule;

public class AndroidApplication extends MultiDexApplication {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
//        Configuration.Builder configurationBuilder = new Configuration.Builder(getBaseContext());
//        ActiveAndroid.initialize(configurationBuilder.create());
    }

    public AppComponent getAppComponent() {
        if (component == null) {
            component = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return component;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
//        ActiveAndroid.dispose();
    }
}