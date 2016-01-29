package com.example.user.weather.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.weather.R;
import com.example.user.weather.databinding.CityBinding;
import com.example.user.weather.model.CityEntity;

public class CityAdapter extends ArrayAdapterBase<CityEntity>{

    public CityAdapter(Context context) {
        super(context, -1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CityBinding binding;

        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.row_city, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (CityBinding) convertView.getTag();
        }

        binding.setCity(getItem(position));
        return convertView;
    }
}