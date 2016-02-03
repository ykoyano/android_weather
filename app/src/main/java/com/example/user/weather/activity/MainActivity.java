package com.example.user.weather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import com.example.user.weather.R;
import com.example.user.weather.adapter.SubFragmentPagerAdapter;
import com.example.user.weather.databinding.ActivityMainBinding;
import com.example.user.weather.fragment.MainFragment;
import com.example.user.weather.logic.GeoLogic;
import com.example.user.weather.model.GeoEntity;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends ActivityBase implements ViewPager.OnPageChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String KEY_GEO = "geo";

    private ActivityMainBinding binding;

    private SubFragmentPagerAdapter adapter;

    List<GeoEntity> geos;

    @Inject
    GeoLogic geoLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getApplicationComponent().inject(this);

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
//        geos = geoLogic.findAll();
//        geoLogic.delete(geoLogic.findAll().get(0));

        HashMap<CharSequence, Fragment> fragments = new HashMap<>();
        for (GeoEntity geoEntity : geos) {
            fragments.put(geoEntity.getCity(), MainFragment.newInstance(geoEntity));
        }
        this.adapter = new SubFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.addOnPageChangeListener(this);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent.getSerializableExtra(KEY_GEO) != null) {
            if(binding.tabLayout.getTabCount() > this.geos.size()){
                binding.tabLayout.removeTabAt(this.geos.size());
                this.adapter.remove(this.geos.size());
            }
            GeoEntity geoEntity = (GeoEntity) intent.getSerializableExtra(KEY_GEO);
            this.adapter.add(geoEntity.getCity(), MainFragment.newInstance(geoEntity));
            this.adapter.notifyDataSetChanged();
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(geoEntity.getCity()));
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position)     {
        Log.d("MainActivity", "onPageSelected() position=" + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private List<GeoEntity> getTestData() {
        List<GeoEntity> cities = new ArrayList<>();
        cities.add(new GeoEntity(0, "さいたま", "110010", 139.569754, 35.8777));
        cities.add(new GeoEntity(0, "栃木", "110010", 139.748078, 36.465153));
        cities.add(new GeoEntity(0, "群馬", "110010", 139.569754, 35.8777));
        cities.add(new GeoEntity(0, "茨木", "110010", 139.748078, 36.465153));
        return cities;
    }
}
