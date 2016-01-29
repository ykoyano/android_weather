package com.example.user.weather.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

public class ArrayAdapterBase<T> extends ArrayAdapter<T> {

    public ArrayAdapterBase(final Context context, final int resource) {
        super(context, resource);
    }
}
