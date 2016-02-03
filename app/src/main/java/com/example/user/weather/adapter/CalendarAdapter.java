package com.example.user.weather.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.weather.R;
import com.example.user.weather.databinding.CalendarBinding;

import java.util.Date;
import java.util.HashSet;

public class CalendarAdapter extends ArrayAdapterBase<Date> {

    private HashSet<Date> eventDays;

    public CalendarAdapter(Context context, HashSet<Date> eventDays) {
        super(context, -1);
        this.eventDays = eventDays;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        CalendarBinding binding;

        if (view == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.control_calendar_day, parent, false);
            view = binding.getRoot();
            view.setTag(binding);
        } else {
            binding = (CalendarBinding) view.getTag();
        }

        Date date = getItem(position);
        int day = date.getDate();
        int month = date.getMonth();
        int year = date.getYear();

        Date today = new Date();

        binding.textView.setBackgroundResource(0);
        if (eventDays != null) {
            for (Date eventDate : eventDays) {
                if (eventDate.getDate() == day &&
                        eventDate.getMonth() == month &&
                        eventDate.getYear() == year) {
                    break;
                }
            }
        }

        binding.textView.setTypeface(null, Typeface.NORMAL);
        binding.textView.setTextColor(Color.BLACK);

        if (day == today.getDate()) {
            binding.textView.setTypeface(null, Typeface.BOLD);
        }

        binding.textView.setText(String.valueOf(date.getDate()));

        return view;
    }
}
