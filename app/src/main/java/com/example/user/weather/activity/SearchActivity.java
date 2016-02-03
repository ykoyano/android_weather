package com.example.user.weather.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.widget.SearchView;
import com.example.user.weather.R;
import com.example.user.weather.databinding.ActivitySearchBinding;
import com.example.user.weather.fragment.SearchFragment;
import com.example.user.weather.fragment.SearchResultFragment;

public class SearchActivity extends ActivityBase {

    private static final String TAG = SearchActivity.class.getSimpleName();

    private ActivitySearchBinding binding;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        binding = bindContentView(this, R.layout.activity_search);

        binding.toolBar.inflateMenu(R.menu.search);
        searchView = (SearchView) binding.toolBar.getMenu().findItem(R.id.menu_search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentByTag(SearchResultFragment.TAG);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if (fragment == null) {
                    transaction.replace(R.id.fragment_container, SearchResultFragment.newInstance(), SearchResultFragment.TAG);
                    transaction.addToBackStack(SearchResultFragment.TAG);
                } else {
                    transaction.attach(fragment);
                }
                transaction.commit();
                return true;
            }
        });

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, SearchFragment.newInstance(), SearchFragment.TAG);
            transaction.commit();
        }
    }
}
















