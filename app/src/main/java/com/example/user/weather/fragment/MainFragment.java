package com.example.user.weather.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.weather.databinding.FragmentMainBinding;
import com.example.user.weather.logic.MyCityLogic;
import com.example.user.weather.logic.TargetCityLogic;
import com.example.user.weather.logic.WeatherLogic;
import com.example.user.weather.model.CityEntity;
import com.example.user.weather.model.WeatherModel;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;

public class MainFragment extends FragmentBase {

    public static final String TAG = MainFragment.class.getSimpleName();

    public MainFragment() {
    }

    @Inject
    WeatherLogic weatherLogic;

    @Inject
    MyCityLogic myCityLogic;

    @Inject
    TargetCityLogic targetCityLogic;

    private FragmentMainBinding binding;

    public static MainFragment newInstance(CityEntity city) {
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appComponent().inject(this);

        Observer observer = new Observer<WeatherModel>() {

            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(WeatherModel weatherModel) {

            }
        };

        weatherLogic.getWeather("200010")
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
