package com.example.user.weather.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.weather.R;
import com.example.user.weather.databinding.PrefectureBinding;
import com.example.user.weather.model.PrefectureEntity;

public class PrefectureAdapter extends ArrayAdapterBase<PrefectureEntity> {

    public PrefectureAdapter(Context context) {
        super(context, -1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PrefectureBinding binding;

        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.row_prefecture, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (PrefectureBinding) convertView.getTag();
        }

        binding.setPrefecture(getItem(position));
        return convertView;
    }
}
