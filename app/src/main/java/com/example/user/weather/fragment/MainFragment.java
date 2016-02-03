package com.example.user.weather.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.weather.R;
import com.example.user.weather.databinding.FragmentMainBinding;
import com.example.user.weather.databinding.FragmentSearchBinding;
import com.example.user.weather.logic.GeoLogic;
import com.example.user.weather.logic.MyCityLogic;
import com.example.user.weather.logic.TargetCityLogic;
import com.example.user.weather.logic.WeatherLogic;
import com.example.user.weather.model.GeoEntity;
import com.example.user.weather.model.weather.InformationEntity;
import com.example.user.weather.model.weather.MainEntity;
import icepick.State;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.List;

public class MainFragment extends FragmentBase {

    public static final String TAG = MainFragment.class.getSimpleName();

    private static final String KEY_GEO = "geo";

    public MainFragment() {
    }

    @State GeoEntity geo;

    @Inject
    WeatherLogic weatherLogic;

    @Inject
    GeoLogic geoLogic;

    public static MainFragment newInstance(GeoEntity geo) {
        Bundle args = new Bundle();
        args.putSerializable(KEY_GEO, geo);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.geo = (GeoEntity) getArguments().getSerializable(KEY_GEO);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final FragmentMainBinding binding = FragmentMainBinding.bind(view);
        appComponent().inject(this);

        Observer observer = new Observer<InformationEntity<MainEntity>>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(InformationEntity information) {
                binding.setInformation(information);
                Observer observer_2 = new Observer<List<GeoEntity>>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<GeoEntity> geos) {
                        binding.setGeo(geos.get(0));
                    }
                };

                geoLogic.getAddressByCoordinate(information.getCity().getCoord().getLon(), information.getCity().getCoord().getLat())
                        .compose(bindToLifecycle())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer_2);
            }
        };

        weatherLogic.getWeather(geo.getX(), geo.getY())
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}

