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
import com.example.user.weather.util.WeatherUtil;

import java.util.List;

public class WeatherRecycleAdapter extends RecyclerView.Adapter<WeatherRecycleAdapter.BindingHolder> {

    private static final String TIME =  "時";
    private static final String NOW =  "現在";

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
        holder.getBinding().weatherTemp.setText(WeatherUtil.toStringTemperature(weather.getMain().getTemp()));

        if(position == 0){
            holder.getBinding().weatherTime.setText(NOW );
        }else {
            holder.getBinding().weatherTime.setText(String.valueOf(DateUtil.unixTimeToDate((weather.getDt())).getHours()) + TIME);
        }

        holder.getBinding().weatherImage.setImageResource(WeatherUtil.getIconResource(weather.getIcon().get(0).getIcon()));
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