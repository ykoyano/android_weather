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
import com.example.user.weather.logic.GeoLogic;
import com.example.user.weather.model.GeoEntity;
import icepick.State;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class CityFragment extends FragmentBase {

    public static final String TAG = CityFragment.class.getSimpleName();

    private static final String KEY_AREA = "area";
    private static final String KEY_PREFECTURE = "prefecture";

    @Inject
    GeoLogic geoLogic;

    @State
    String area;

    @State
    String prefecture;

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
        final FragmentCityBinding binding = FragmentCityBinding.bind(view);
        appComponent().inject(this);

        Observer observer = new Observer<List<GeoEntity>>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<GeoEntity> geos) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext().getApplicationContext(), android.R.layout.simple_list_item_1, distinct(geos));
                binding.listView.setAdapter(adapter);
            }
        };

        geoLogic.getCities(area, prefecture).compose(bindToLifecycle()).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);

        binding.listView.setOnItemClickListener((parent, listenerView, position, id) -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra("city", (String) parent.getItemAtPosition((int) id));
            startActivity(intent);
        });
    }

    private ArrayList<String> distinct(List<GeoEntity> slist) {
        List<String> cities = Observable.from(slist).map(GeoEntity::getCity).toList().toBlocking().single();
        LinkedHashSet<String> set = new LinkedHashSet<>(cities);
        return new ArrayList<>(set);
    }
}