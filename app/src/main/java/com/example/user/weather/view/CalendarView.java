package com.example.user.weather.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.user.weather.R;
import com.example.user.weather.adapter.CalendarAdapter;
import com.example.user.weather.model.weather.DayEntity;
import com.example.user.weather.model.weather.LongWeatherEntity;
import com.example.user.weather.model.weather.WeatherEntity;
import com.example.user.weather.util.DateUtil;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class CalendarView extends LinearLayout {

    private static final int DAYS_COUNT = 21;

    private static final String DATE_FORMAT = "MMM yyyy";

    private String dateFormat;

    private Calendar currentDate = Calendar.getInstance();

    private EventHandler eventHandler = null;

    private LinearLayout header;
    private TextView txtDate;
    private GridView grid;

    public CalendarView(Context context) {
        super(context);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context, attrs);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs);
    }

    private void initControl(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.control_calendar, this);

        loadDateFormat(attrs);
        assignUiElements();
        assignClickHandlers();

//        updateCalendar();
    }

    private void loadDateFormat(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CalendarView);

        try {
            dateFormat = ta.getString(R.styleable.CalendarView_dateFormat);
            if (dateFormat == null)
                dateFormat = DATE_FORMAT;
        } finally {
            ta.recycle();
        }
    }

    private void assignUiElements() {
        header = (LinearLayout) findViewById(R.id.calendar_header);
        txtDate = (TextView) findViewById(R.id.calendar_date_display);
        grid = (GridView) findViewById(R.id.calendar_grid);
    }

    private void assignClickHandlers() {
        grid.setOnItemLongClickListener((view, cell, position, id) -> {
            if (eventHandler == null)
                return false;

            eventHandler.onDayLongPress((Date) view.getItemAtPosition(position));
            return true;
        });
    }

    public void updateCalendar() {
        updateCalendar(null);
    }

    public void updateCalendar(List<LongWeatherEntity> longWeatherList) {

        ArrayList<DayEntity> dayList = new ArrayList<>();
        Calendar calendar = (Calendar) currentDate.clone();

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

        while (dayList.size() < DAYS_COUNT) {
            Date cell = DateUtils.truncate(calendar.getTime(), Calendar.DAY_OF_MONTH);
            if (longWeatherList.size() > 0) {
                LongWeatherEntity longWeatherEntity = longWeatherList.get(0);
                Date weather = DateUtils.truncate(DateUtil.unixTimeToDate(longWeatherEntity.getDt()), Calendar.DAY_OF_MONTH);
                if (weather.compareTo(cell) == 0) {
                    dayList.add(new DayEntity(cell, longWeatherEntity));
                    longWeatherList.remove(0);
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    continue;
                }
            }
            dayList.add(new DayEntity(cell, null));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        CalendarAdapter adapter = new CalendarAdapter(getContext());
        adapter.addAll(dayList);
        grid.setAdapter(adapter);

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        txtDate.setText(sdf.format(currentDate.getTime()));

        int month = currentDate.get(Calendar.MONTH);
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public interface EventHandler {

        void onDayLongPress(Date date);
    }
}
