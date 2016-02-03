package com.example.user.weather.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.example.user.weather.R;
import com.example.user.weather.databinding.FragmentPrefectureBinding;
import com.example.user.weather.logic.GeoLogic;
import icepick.State;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.List;

public class PrefectureFragment extends FragmentBase{

    public static final String TAG = PrefectureFragment.class.getSimpleName();

    private static final String KEY_AREA = "area";

    @Inject
    GeoLogic geoLogic;

    @State
    String area;

    public static PrefectureFragment newInstance(String area) {
        PrefectureFragment fragment = new PrefectureFragment();
        Bundle args = new Bundle();
        args.putString(KEY_AREA, area);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.area = getArguments().getString(KEY_AREA);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_prefecture, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final FragmentPrefectureBinding binding = FragmentPrefectureBinding.bind(view);
        appComponent().inject(this);

        Observer observer = new Observer<List<String>>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<String> prefectures) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext().getApplicationContext(), android.R.layout.simple_list_item_1, prefectures);
                binding.listView.setAdapter(adapter);
            }
        };

        geoLogic.getPrefectures(area)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        binding.listView.setOnItemClickListener((parent, listenerView, position, id) -> {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, CityFragment.newInstance(area, (String) parent.getItemAtPosition((int) id)), CityFragment.TAG);
            transaction.addToBackStack(PrefectureFragment.TAG);
            transaction.commit();
        });
    }
}
