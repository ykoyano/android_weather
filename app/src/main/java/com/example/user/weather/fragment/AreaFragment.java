package com.example.user.weather.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.example.user.weather.R;
import com.example.user.weather.databinding.FragmentAreaBinding;
import com.example.user.weather.logic.LocationLogic;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.List;

public class AreaFragment extends FragmentBase {

    public static final String TAG = AreaFragment.class.getSimpleName();

    public AreaFragment() {
    }

    @Inject
    LocationLogic locationLogic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_area, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final FragmentAreaBinding binding = FragmentAreaBinding.bind(view);
        appComponent().inject(this);

        binding.toolBar.setTitle(R.string.area);
        binding.toolBar.inflateMenu(R.menu.select);
        binding.toolBar.setNavigationIcon(R.drawable.ic_arrow_back_48px);
        binding.toolBar.setNavigationOnClickListener(v -> {
            getActivity().finish();
        });

        binding.toolBar.setOnMenuItemClickListener(menuItem -> {
            getActivity().finish();
            return true;
        });

        Observer observer = new Observer<List<String>>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<String> cities) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext().getApplicationContext(), R.layout.row_string, cities);
                binding.listView.setAdapter(adapter);
            }
        };

        locationLogic.getAreas()
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        binding.listView.setOnItemClickListener((parent, listenerView, position, id) -> {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, PrefectureFragment.newInstance((String) parent.getItemAtPosition((int) id)), PrefectureFragment.TAG);
            transaction.addToBackStack(PrefectureFragment.TAG);
            transaction.commit();
        });
    }
}
