package com.example.user.weather.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.weather.R;
import com.example.user.weather.databinding.FragmentMainBinding;
import com.example.user.weather.model.CityEntity;

public class MainFragment extends FragmentBase {

    public static final String TAG = MainFragment.class.getSimpleName();

    public MainFragment() {
    }

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
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final FragmentMainBinding binding = FragmentMainBinding.bind(view);
    }
}
