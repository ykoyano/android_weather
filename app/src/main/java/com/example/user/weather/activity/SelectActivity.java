package com.example.user.weather.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.example.user.weather.R;

import com.example.user.weather.databinding.ActivitySelectBinding;
import com.example.user.weather.fragment.AreaFragment;
import com.example.user.weather.fragment.SearchFragment;

public class SelectActivity extends ActivityBase{

    private static final String TAG = SelectActivity.class.getSimpleName();

    private ActivitySelectBinding binding;

    private SearchFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        binding = bindContentView(this, R.layout.activity_select);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, new AreaFragment(), AreaFragment.TAG);
            transaction.commit();
        }

    }

}
