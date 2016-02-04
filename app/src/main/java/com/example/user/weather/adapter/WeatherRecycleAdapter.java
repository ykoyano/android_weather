package com.example.user.weather.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.weather.R;
import com.example.user.weather.databinding.WeatherBinding;
import com.example.user.weather.model.weather.WeatherEntity;
import com.example.user.weather.util.DateUtil;

import java.util.List;

public class WeatherRecycleAdapter extends RecyclerView.Adapter<WeatherRecycleAdapter.BindingHolder> {

    private static final double KELVIN = 273.15f;

    private List<WeatherEntity> weathers;

    public WeatherRecycleAdapter(List<WeatherEntity> weathers) {
        this.weathers = weathers;
    }

    public void addAllItem(List<WeatherEntity> weathers) {
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
        holder.getBinding().weatherTemp.setText(String.valueOf((int)(weather.getMain().getTemp() - KELVIN)));
        holder.getBinding().weatherTime.setText(String.valueOf(DateUtil.unixTimeToDate((weather.getDt())).getHours()));

        switch (weather.getIcon().get(0).getIcon()) {
        case "01d":
            holder.getBinding().weatherImage.setImageResource(R.drawable.sun);
            break;
        case "02d":
            holder.getBinding().weatherImage.setImageResource(R.drawable.herf);
            break;
        case "03d":
        case "03n":
            holder.getBinding().weatherImage.setImageResource(R.drawable.cloud);
            break;
        case "04d":
        case "04n":
            holder.getBinding().weatherImage.setImageResource(R.drawable.double_cloud);
            break;
        case "01n":
            holder.getBinding().weatherImage.setImageResource(R.drawable.moon);
            break;
        case "02n":
            holder.getBinding().weatherImage.setImageResource(R.drawable.moon_and_cloud);
            break;
        case "09d":
        case "09n":
            holder.getBinding().weatherImage.setImageResource(R.drawable.rain);
            break;
        case "10d":
        case "10n":
            holder.getBinding().weatherImage.setImageResource(R.drawable.rain_and_moon );
            break;
        case "11d":
        case "11n":
            holder.getBinding().weatherImage.setImageResource(R.drawable.squall);
            break;
        case "13d":
        case "13n":
            holder.getBinding().weatherImage.setImageResource(R.drawable.snow);
            break;
        default:
            throw new IllegalArgumentException("InvalidValue=" + weather.getIcon().get(0).getIcon());
        }

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