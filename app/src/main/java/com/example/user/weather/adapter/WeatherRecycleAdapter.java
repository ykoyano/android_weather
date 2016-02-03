package com.example.user.weather.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.weather.R;
import com.example.user.weather.databinding.WeatherBinding;
import com.example.user.weather.model.weather.WeatherEntity;

import java.util.List;

public class WeatherRecycleAdapter extends RecyclerView.Adapter<WeatherRecycleAdapter.BindingHolder> {

    private static final double KELVIN = 273.15;

    private List<WeatherEntity> weathers;

    public WeatherRecycleAdapter(List<WeatherEntity> weathers) {
        this.weathers = weathers;
    }

    public void addAllItem(List<WeatherEntity> weathers){
        this.weathers.addAll(weathers);
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.col_weather, parent, false);
        return new BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final WeatherEntity weather = weathers.get(position);
        holder.getBinding().setWeather(weather);
        holder.getBinding().weatherTemp.setText(String.valueOf(weather.getMain().getTemp() - KELVIN));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {

        private WeatherBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public WeatherBinding getBinding() {
            return binding;
        }
    }
}