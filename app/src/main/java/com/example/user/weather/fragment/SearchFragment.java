package com.example.user.weather.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.weather.R;
import com.example.user.weather.activity.SelectActivity;
import com.example.user.weather.databinding.FragmentSearchBinding;

public class SearchFragment extends FragmentBase {

    public static final String TAG = SearchFragment.class.getSimpleName();

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final FragmentSearchBinding binding = FragmentSearchBinding.bind(view);

        binding.selectArea.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SelectActivity.class);
            startActivity(intent);
        });

        binding.currentLocation.setOnClickListener(v -> {
        });

        binding.listView.setOnItemClickListener((parent, listenerView, position, id) -> {
        });
    }

}
