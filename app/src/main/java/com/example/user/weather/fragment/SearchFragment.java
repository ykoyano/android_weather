package com.example.user.weather.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.weather.R;
import com.example.user.weather.databinding.FragmentSearchBinding;

public class SearchFragment extends FragmentBase {

    public static final String TAG = SearchFragment.class.getSimpleName();

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final FragmentSearchBinding binding = FragmentSearchBinding.bind(view);

        binding.listView.setOnItemClickListener((parent, listenerView, position, id) -> {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            PrefectureFragment prefectureFragment = new PrefectureFragment();
            transaction.replace(R.id.fragment_container, prefectureFragment, PrefectureFragment.TAG);
            transaction.addToBackStack(PrefectureFragment.TAG);
            transaction.commit();
        });
    }
}
