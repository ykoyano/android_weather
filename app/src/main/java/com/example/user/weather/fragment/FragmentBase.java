package com.example.user.weather.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.example.user.weather.AndroidApplication;
import com.example.user.weather.component.AppComponent;
import com.trello.rxlifecycle.FragmentEvent;
import com.trello.rxlifecycle.RxLifecycle;
import icepick.Icepick;
import rx.Observable;
import rx.subjects.BehaviorSubject;

public abstract class FragmentBase extends Fragment {

    private final BehaviorSubject<FragmentEvent> lifecycle = BehaviorSubject.create();

    protected AppComponent appComponent() {
        return ((AndroidApplication) getActivity().getApplication()).getAppComponent();
    }

    public final <T> Observable.Transformer<? super T, ? extends T> bindUntilEvent(FragmentEvent event) {
        return RxLifecycle.bindUntilFragmentEvent(lifecycle, event);
    }

    public final <T> Observable.Transformer<? super T, ? extends T> bindToLifecycle() {
        return RxLifecycle.bindFragment(lifecycle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        lifecycle.onNext(FragmentEvent.CREATE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }
}