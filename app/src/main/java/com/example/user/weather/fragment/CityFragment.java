package com.example.user.weather.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.example.user.weather.R;
import com.example.user.weather.activity.MainActivity;
import com.example.user.weather.databinding.FragmentCityBinding;
import com.example.user.weather.logic.LocationLogic;
import com.example.user.weather.model.Location;
import com.example.user.weather.util.LocationUtil;
import icepick.State;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;

import java.util.List;

public class CityFragment extends FragmentBase {

    public static final String TAG = CityFragment.class.getSimpleName();

    private static final String KEY_AREA = "area";
    private static final String KEY_PREFECTURE = "prefecture";
    private static final String KEY_LOCATION = "location";

    @Inject
    LocationLogic locationLogic;

    @State
    String area;

    @State
    String prefecture;

    FragmentCityBinding binding;

    public static CityFragment newInstance(String area, String prefecture) {
        CityFragment fragment = new CityFragment();
        Bundle args = new Bundle();
        args.putString(KEY_AREA, area);
        args.putString(KEY_PREFECTURE, prefecture);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.area = (String) getArguments().get(KEY_AREA);
        this.prefecture = (String) getArguments().get(KEY_PREFECTURE);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentCityBinding.bind(view);
        appComponent().inject(this);

        binding.toolBar.setTitle(R.string.city);
        binding.toolBar.inflateMenu(R.menu.select);
        binding.toolBar.setNavigationIcon(R.drawable.ic_arrow_back_48px);
        binding.toolBar.setNavigationOnClickListener(v -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });

        binding.toolBar.setOnMenuItemClickListener(menuItem -> {
            getActivity().finish();
            return true;
        });

        Observer observer = new Observer<List<Location>>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<Location> locations) {
//                locationLogic.save(locations.get(0));
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra(KEY_LOCATION, locations.get(0));
                startActivity(intent);
            }
        };

        getCities();

        binding.listView.setOnItemClickListener((parent, listenerView, position, id) -> {
            locationLogic.getAddressByKeyword((String) parent.getItemAtPosition((int) id))
                    .compose(bindToLifecycle())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        });
    }

    private void getCities(){
        Observer observer = new Observer<List<Location>>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<Location> locations) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext().getApplicationContext(), R.layout.row_string, LocationUtil.toStringArray(locations));
                binding.listView.setAdapter(adapter);
            }
        };

        locationLogic.getCities(area, prefecture)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}