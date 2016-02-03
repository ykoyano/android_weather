package com.example.user.weather.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.weather.R;
import com.example.user.weather.databinding.LocationBinding;
import com.example.user.weather.model.Location;

public class LocationAdapter extends ArrayAdapterBase<Location> {

    public LocationAdapter(Context context) {
        super(context, -1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LocationBinding binding;

        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.row_location, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (LocationBinding) convertView.getTag();
        }

        binding.setLocation(getItem(position));
        return convertView;
    }
}