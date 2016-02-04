package com.example.user.weather.activity;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ArrayAdapter;
import com.example.user.weather.R;
import com.example.user.weather.databinding.ActivitySearchBinding;
import com.example.user.weather.logic.LocationLogic;
import com.example.user.weather.model.Location;
import com.example.user.weather.util.LocationUtil;
import org.apache.commons.lang3.StringUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends ActivityBase {

    private static final String TAG = SearchActivity.class.getSimpleName();

    private static final String KEY_LOCATION = "location";

    private ActivitySearchBinding binding;

    private SearchView searchView;

    @Inject
    LocationLogic locationLogic;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getApplicationComponent().inject(this);

        binding = bindContentView(this, R.layout.activity_search);
        binding.toolBar.inflateMenu(R.menu.search);
        adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.row_string, new ArrayList<>());
        binding.resultListView.setAdapter(adapter);

        binding.toolBar.setNavigationIcon(R.drawable.ic_arrow_back_48px);
        binding.toolBar.setNavigationOnClickListener(v -> {
            finish();
        });

        searchView = (SearchView) binding.toolBar.getMenu().findItem(R.id.menu_search).getActionView();
        searchView.setQueryHint(getString(R.string.please_type_area));
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String searchWord) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchWord) {
                if (StringUtils.isBlank(searchWord)) {
                    binding.selectLayout.setVisibility(View.VISIBLE);
                    binding.searchLayout.setVisibility(View.GONE);
                } else {
                    binding.selectLayout.setVisibility(View.GONE);
                    binding.searchLayout.setVisibility(View.VISIBLE);

                    Observer observer = new Observer<List<Location>>() {

                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(List<Location> locations) {
                            adapter.clear();
                            adapter.addAll(LocationUtil.toStringArray(locations));
                            adapter.notifyDataSetChanged();
                            binding.resultListView.setAdapter(adapter);
                        }
                    };

                    getAddressByKeyword(searchWord, observer);
                }
                return true;
            }
        });

        binding.selectLayout.setOnClickListener(v -> {
            Intent intent = new Intent(this, SelectActivity.class);
            startActivity(intent);
        });

        binding.resultListView.setOnItemClickListener((parent, listenerView, position, id) -> {
            Observer observer = new Observer<List<Location>>() {

                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(List<Location> locations) {
                    Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                    intent.putExtra(KEY_LOCATION, locations.get(0));
                    startActivity(intent);
                }
            };
            getAddressByKeyword((String) parent.getItemAtPosition((int) id), observer);
        });
    }

    private void getAddressByKeyword(String searchWord, Observer observer){
        locationLogic.getAddressByKeyword(searchWord)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}