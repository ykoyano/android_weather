package com.example.user.weather.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.weather.R;
import com.example.user.weather.adapter.CityAdapter;
import com.example.user.weather.databinding.FragmentCityBinding;
import com.example.user.weather.model.PrefectureEntity;

public class CityFragment extends FragmentBase{

    public static final String TAG = CityFragment.class.getSimpleName();

    private PrefectureEntity prefectureEntity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.prefectureEntity = (PrefectureEntity) getArguments().get("prefecture");
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final FragmentCityBinding binding = FragmentCityBinding.bind(view);

        CityAdapter cityAdapter = new CityAdapter(getContext().getApplicationContext());
        cityAdapter.addAll(this.prefectureEntity.getCities());
        binding.listView.setAdapter(cityAdapter);
        binding.listView.setOnItemClickListener((parent, listenerView, position, id) -> {
        });
    }
}
