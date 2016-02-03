package com.example.user.weather.component;

import android.content.Context;
import com.example.user.weather.activity.MainActivity;
import com.example.user.weather.activity.SearchActivity;
import com.example.user.weather.fragment.AreaFragment;
import com.example.user.weather.fragment.CityFragment;
import com.example.user.weather.fragment.MainFragment;
import com.example.user.weather.fragment.PrefectureFragment;
import com.example.user.weather.module.AppModule;
import com.example.user.weather.module.DateLayerModule;
import com.example.user.weather.module.LogicLayerModule;
import com.example.user.weather.module.NetworkModule;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        AppModule.class,
        LogicLayerModule.class,
        NetworkModule.class,
        DateLayerModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
    void inject(MainFragment mainFragment);
    void inject(SearchActivity searchActivity);
    void inject(AreaFragment areaFragment);
    void inject(PrefectureFragment prefectureFragment);
    void inject(CityFragment cityFragment);

    Context context();
}
