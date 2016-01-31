package com.example.user.weather.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;

import com.example.user.weather.R;
import com.example.user.weather.databinding.ActivitySearchBinding;
import com.example.user.weather.fragment.SearchFragment;

public class SearchActivity extends ActivityBase {

    private static final String TAG = SearchActivity.class.getSimpleName();

    private ActivitySearchBinding binding;

    private SearchFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        binding = bindContentView(this, R.layout.activity_search);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, new SearchFragment(), SearchFragment.TAG);
            transaction.commit();
        }

    }
}
















