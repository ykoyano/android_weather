package com.example.user.weather.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import com.example.user.weather.R;
import com.example.user.weather.databinding.ActivityMainBinding;
import com.example.user.weather.fragment.MainFragment;
import com.example.user.weather.model.GeoEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActivityBase implements ViewPager.OnPageChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;

    List<GeoEntity> geos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        binding = bindContentView(this, R.layout.activity_main);

        binding.toolBar.inflateMenu(R.menu.main_menu);

        binding.toolBar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
            case R.id.search_icon:
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
            return true;
        });

        geos = getTestData();

        binding.viewPager.setAdapter(makeFragmentPagerAdapter());
        binding.viewPager.addOnPageChangeListener(this);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if(intent.getSerializableExtra("geo") != null){
            this.geos.add((GeoEntity) intent.getSerializableExtra("geo"));
        }
    }

    private FragmentPagerAdapter makeFragmentPagerAdapter() {
        return new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return MainFragment.newInstance(geos.get(position));
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return geos.get(position).getCity();
            }

            @Override
            public int getCount() {
                return  geos.size();
            }
        };
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("MainActivity", "onPageSelected() position=" + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private List<GeoEntity> getTestData() {
        List<GeoEntity> cities = new ArrayList<>();
        cities.add(new GeoEntity("さいたま", "110010", 1 ,1));
        cities.add(new GeoEntity("さいたま", "110010", 1 ,1));
        cities.add(new GeoEntity("さいたま", "110010", 1 ,1));
        return cities;
    }
}
