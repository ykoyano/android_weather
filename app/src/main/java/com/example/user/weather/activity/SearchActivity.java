package com.example.user.weather.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import com.example.user.weather.R;
import com.example.user.weather.adapter.PrefectureAdapter;
import com.example.user.weather.databinding.ActivitySearchBinding;
import com.example.user.weather.model.CityEntity;
import com.example.user.weather.model.PrefectureEntity;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends ActivityBase {

    private static final String TAG = SearchActivity.class.getSimpleName();

    private ActivitySearchBinding binding;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);

        binding.toolBar.inflateMenu(R.menu.search);
        searchView = (SearchView) binding.toolBar.getMenu().findItem(R.id.menu_search).getActionView();

        PrefectureAdapter prefectureAdapter = new PrefectureAdapter(getApplicationContext());
        prefectureAdapter.addAll(getTestData());
        binding.listView.setAdapter(prefectureAdapter);
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
















