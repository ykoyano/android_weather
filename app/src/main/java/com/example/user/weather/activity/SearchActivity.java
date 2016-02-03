package com.example.user.weather.activity;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import com.example.user.weather.R;
import com.example.user.weather.databinding.ActivitySearchBinding;
import com.example.user.weather.logic.GeoLogic;
import com.example.user.weather.model.GeoEntity;
import org.apache.commons.lang3.StringUtils;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class SearchActivity extends ActivityBase {

    private static final String TAG = SearchActivity.class.getSimpleName();

    private ActivitySearchBinding binding;

    private SearchView searchView;

    @Inject
    GeoLogic geoLogic;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getApplicationComponent().inject(this);

        binding = bindContentView(this, R.layout.activity_search);
        binding.toolBar.inflateMenu(R.menu.search);
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, new ArrayList<>());
        binding.resultListView.setAdapter(adapter);
        searchView = (SearchView) binding.toolBar.getMenu().findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (StringUtils.isBlank(s)) {
                    binding.selectLayout.setVisibility(View.VISIBLE);
                    binding.searchLayout.setVisibility(View.GONE);
                } else {
                    binding.selectLayout.setVisibility(View.GONE);
                    binding.searchLayout.setVisibility(View.VISIBLE);

                    Observer observer = new Observer<List<GeoEntity>>() {

                        @Override
                        public void onCompleted() {
                            Log.d(TAG, "onCompleted()");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, "onError()");
                        }

                        @Override
                        public void onNext(List<GeoEntity> geos) {
                            adapter.clear();
                            adapter.addAll(distinct(geos));
                            adapter.notifyDataSetChanged();
                            binding.resultListView.setAdapter(adapter);
                        }
                    };

                    geoLogic.getAddressByKeyword(s)
                            .compose(bindToLifecycle())
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(observer);
                }
                return true;
            }
        });

        binding.selectArea.setOnClickListener(v -> {
            Intent intent = new Intent(this, SelectActivity.class);
            startActivity(intent);
        });
    }

    private ArrayList<String> distinct(List<GeoEntity> slist) {
        List<String> cities = Observable.from(slist).map(GeoEntity::getCity).toList().toBlocking().single();
        LinkedHashSet<String> set = new LinkedHashSet<>(cities);
        return new ArrayList<>(set);
    }
}