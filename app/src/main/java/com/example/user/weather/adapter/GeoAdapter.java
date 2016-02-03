package com.example.user.weather.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.weather.R;
import com.example.user.weather.databinding.GeoBinding;
import com.example.user.weather.model.GeoEntity;

public class GeoAdapter extends ArrayAdapterBase<GeoEntity> {

    public GeoAdapter(Context context) {
        super(context, -1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GeoBinding binding;

        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.row_geo, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (GeoBinding) convertView.getTag();
        }

        binding.setGeo(getItem(position));
        return convertView;
    }
}