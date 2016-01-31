package com.example.user.weather.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import      com.example.user.weather.R;
import com.example.user.weather.adapter.PrefectureAdapter;
import com.example.user.weather.databinding.FragmentPrefectureBinding;
import com.example.user.weather.model.CityEntity;
import com.example.user.weather.model.PrefectureEntity;

import java.util.ArrayList;
import java.util.List;

public class PrefectureFragment extends FragmentBase{

    public static final String TAG = PrefectureFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_prefecture, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final FragmentPrefectureBinding binding = FragmentPrefectureBinding.bind(view);

//        binding.toolBar.inflateMenu(R.menu.search);

        PrefectureAdapter prefectureAdapter = new PrefectureAdapter(getContext().getApplicationContext());
        prefectureAdapter.addAll(getTestData());
        binding.listView.setAdapter(prefectureAdapter);

        binding.listView.setOnItemClickListener((parent, listenerView, position, id) -> {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            Bundle args = new Bundle();
            args.putSerializable("prefecture", (PrefectureEntity) parent.getItemAtPosition((int) id));
            CityFragment cityFragment = new CityFragment();
            cityFragment.setArguments(args);
            transaction.replace(R.id.fragment_container, cityFragment, CityFragment.TAG);
            transaction.addToBackStack(PrefectureFragment.TAG);
            transaction.commit();
        });
    }

    private List<PrefectureEntity> getTestData() {
        List<PrefectureEntity> list = new ArrayList<>();

        List<CityEntity> a_1 = new ArrayList<>();
        a_1.add(new CityEntity("さいたま", "110010"));
        a_1.add(new CityEntity("熊谷", "110020"));
        a_1.add(new CityEntity("秩父", "110030"));
        list.add(new PrefectureEntity("埼玉県", a_1));

        List<CityEntity> a_2 = new ArrayList<>();
        a_2.add(new CityEntity("千葉", "120010"));
        a_2.add(new CityEntity("銚子", "120020"));
        a_2.add(new CityEntity("館山", "120030"));
        list.add(new PrefectureEntity("千葉県", a_2));

        List<CityEntity> a_3 = new ArrayList<>();
        a_3.add(new CityEntity("東京", "130010"));
        a_3.add(new CityEntity("大島", "130020"));
        a_3.add(new CityEntity("八丈島", "130030"));
        list.add(new PrefectureEntity("東京都", a_3));

        return list;
    }
}
