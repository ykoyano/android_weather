package com.example.user.weather.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.weather.R;
import com.example.user.weather.databinding.FragmentSearchBinding;

public class SearchFragment extends FragmentBase {

    public static final String TAG = SearchFragment.class.getSimpleName();

    private SearchView searchView;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final FragmentSearchBinding binding = FragmentSearchBinding.bind(view);

        binding.toolBar.inflateMenu(R.menu.search);
        searchView = (SearchView) binding.toolBar.getMenu().findItem(R.id.menu_search).getActionView();

        binding.selectArea.setOnClickListener(v -> {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            AreaFragment areaFragment = new AreaFragment();
            transaction.replace(R.id.fragment_container, areaFragment, AreaFragment.TAG);
            transaction.addToBackStack(AreaFragment.TAG);
            transaction.commit();
        });

        binding.currentLocation.setOnClickListener(v -> {
        });

        binding.listView.setOnItemClickListener((parent, listenerView, position, id) -> {
        });
    }

}
