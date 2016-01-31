package com.example.user.weather.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.RxLifecycle;
import icepick.Icepick;
import rx.Observable;
import rx.subjects.BehaviorSubject;

public abstract class ActivityBase extends AppCompatActivity {

    private final BehaviorSubject<ActivityEvent> lifecycle = BehaviorSubject.create();

    protected final <T> Observable.Transformer<? super T, ? extends T> bindUntilEvent(ActivityEvent event) {
        return RxLifecycle.bindUntilActivityEvent(lifecycle, event);
    }

    protected final <T> Observable.Transformer<? super T, ? extends T> bindToLifecycle() {
        return RxLifecycle.bindActivity(lifecycle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        lifecycle.onNext(ActivityEvent.CREATE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }
}