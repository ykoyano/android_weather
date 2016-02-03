package com.example.user.weather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.example.user.weather.R;
import com.example.user.weather.adapter.SubFragmentPagerAdapter;
import com.example.user.weather.databinding.ActivityMainBinding;
import com.example.user.weather.fragment.MainFragment;
import com.example.user.weather.logic.LocationLogic;
import com.example.user.weather.model.Location;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends ActivityBase {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String KEY_LOCATION = "location";

    private ActivityMainBinding binding;

    private SubFragmentPagerAdapter adapter;

    List<Location> locations;

    @Inject
    LocationLogic locationLogic;

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

        locations = locationLogic.findAll();

        HashMap<CharSequence, Fragment> fragments = new HashMap<>();
        for (Location location : locations) {
            fragments.put(location.getCity(), MainFragment.newInstance(location));
        }
        this.adapter = new SubFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent.getSerializableExtra(KEY_LOCATION) != null) {
            if(binding.tabLayout.getTabCount() > this.locations.size()){
                binding.tabLayout.removeTabAt(this.locations.size());
                this.adapter.remove(this.locations.size());
            }
            Location location = (Location) intent.getSerializableExtra(KEY_LOCATION);
            this.adapter.add(location.getCity(), MainFragment.newInstance(location));
            this.adapter.notifyDataSetChanged();
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(location.getCity()));
        }
    }

//    private List<Location> getTestData() {
//        List<Location> cities = new ArrayList<>();
//        cities.add(new Location(0, "さいたま", "110010", 139.569754, 35.8777));
//        cities.add(new Location(0, "栃木", "110010", 139.748078, 36.465153));
//        return cities;
//    }
}
