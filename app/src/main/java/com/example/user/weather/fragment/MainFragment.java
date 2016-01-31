package com.example.user.weather.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.weather.databinding.FragmentMainBinding;
import com.example.user.weather.logic.WeatherLogic;
import com.example.user.weather.model.CityEntity;

import javax.inject.Inject;

public class MainFragment extends FragmentBase {

    public static final String TAG = MainFragment.class.getSimpleName();

    public MainFragment() {
    }

//    @Inject
//    WeatherLogic weatherLogic;

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
    }
}
