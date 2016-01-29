package com.example.user.weather.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.trello.rxlifecycle.FragmentEvent;
import com.trello.rxlifecycle.RxLifecycle;
import rx.Observable;
import rx.subjects.BehaviorSubject;

public class FragmentBase extends Fragment {

    private final BehaviorSubject<FragmentEvent> lifecycle = BehaviorSubject.create();

    public final <T> Observable.Transformer<? super T, ? extends T> bindUntilEvent(FragmentEvent event) {
        return RxLifecycle.bindUntilFragmentEvent(lifecycle, event);
    }

    public final <T> Observable.Transformer<? super T, ? extends T> bindToLifecycle() {
        return RxLifecycle.bindFragment(lifecycle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycle.onNext(FragmentEvent.CREATE);
    }
}