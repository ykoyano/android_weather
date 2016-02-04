package com.example.user.weather.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.weather.R;
import com.example.user.weather.adapter.WeatherRecycleAdapter;
import com.example.user.weather.databinding.FragmentMainBinding;
import com.example.user.weather.logic.LocationLogic;
import com.example.user.weather.logic.WeatherLogic;
import com.example.user.weather.model.Location;
import com.example.user.weather.model.weather.InformationEntity;
import com.example.user.weather.model.weather.LongInformationEntity;
import com.example.user.weather.util.WeatherUtil;
import icepick.State;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class MainFragment extends FragmentBase {

    public static final String TAG = MainFragment.class.getSimpleName();

    private static final String KEY_LOCATION = "location";

    public MainFragment() {
    }

    @State
    Location location;

    @Inject
    WeatherLogic weatherLogic;

    @Inject
    LocationLogic locationLogic;

    private WeatherRecycleAdapter adapter;

    private FragmentMainBinding binding;

    public static MainFragment newInstance(Location location) {
        Bundle args = new Bundle();
        args.putSerializable(KEY_LOCATION, location);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.location = (Location) getArguments().getSerializable(KEY_LOCATION);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMainBinding.bind(view);
        appComponent().inject(this);

        this.adapter = new WeatherRecycleAdapter(new ArrayList<>());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager.scrollToPosition(0);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setHasFixedSize(true);

        getDailyWeather();

        HashSet<Date> events = new HashSet<>();
        events.add(new Date());
        binding.calendarView.updateCalendar(events);
    }

    private void getDailyWeather(){
        Observer observer = new Observer<InformationEntity>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(InformationEntity information) {
                getAddressByCoordinate(information);
                binding.setInformation(information);
                adapter.addAllItem(information.getList());
                adapter.notifyDataSetChanged();
                binding.weatherImage.setImageResource(WeatherUtil.getIconResource(information.getList().get(0).getIcon().get(0).getIcon()));
                binding.tempText.setText(WeatherUtil.toStringTemperature(information.getList().get(0).getMain().getTemp()));
            }
        };

        weatherLogic.getDailyWeather(location.getLon(), location.getLat())
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private void getWeekWeather(){
        Observer observer = new Observer<LongInformationEntity>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(LongInformationEntity longInformation) {

            }
        };

        weatherLogic.getWeekWeather(location.getLon(), location.getLat())
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private void getAddressByCoordinate(InformationEntity information){

        Observer observer = new Observer<List<Location>>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<Location> locations) {
                binding.setLocation(locations.get(0));
            }
        };

        locationLogic.getAddressByCoordinate(information.getCity().getCoord().getLon(), information.getCity().getCoord().getLat())
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

