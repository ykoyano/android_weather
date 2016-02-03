package com.example.user.weather.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.weather.R;
import com.example.user.weather.adapter.GeoAdapter;
import com.example.user.weather.databinding.FragmentSearchResultBinding;
import com.example.user.weather.logic.GeoLogic;
import com.example.user.weather.model.GeoEntity;
import icepick.State;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.List;

public class SearchResultFragment extends FragmentBase {

    public static final String TAG = SearchResultFragment.class.getSimpleName();

    private static final String KEY_KEYWORD = "keyword";

    @State
    String keyword;

    public static SearchResultFragment newInstance(String keyword) {
        PrefectureFragment fragment = new PrefectureFragment();
        Bundle args = new Bundle();
        args.putString(KEY_KEYWORD, keyword);
        fragment.setArguments(args);
        return new SearchResultFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.keyword = getArguments().getString(KEY_KEYWORD);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_result, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final FragmentSearchResultBinding binding = FragmentSearchResultBinding.bind(view);

        Observer observer = new Observer<List<GeoEntity>>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<GeoEntity> geos) {
                GeoAdapter adapter = new GeoAdapter(getContext().getApplicationContext());
                adapter.addAll();
                binding.listView.setAdapter(adapter);
            }
        };
    }
}
