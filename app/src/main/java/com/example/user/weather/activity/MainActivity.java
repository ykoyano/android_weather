package com.example.user.weather.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import com.afollestad.materialdialogs.MaterialDialog;
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
    private static final String INIT_STATE = "initState";

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

//      locations = locationLogic.findAll();
        locations = new ArrayList<>();
        
        HashMap<CharSequence, Fragment> fragments = new HashMap<>();
        for (Location location : locations) {
            fragments.put(location.getCity(), MainFragment.newInstance(location));
        }
        this.adapter = new SubFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!getInitState()) {
            new MaterialDialog.Builder(this)
                    .title(R.string.introduction_title)
                    .content(R.string.introduction_content)
                    .onAny((dialog, which) -> {
                        setInitState();
                        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                        startActivity(intent);
                    }).show();
        } else if (locations.size() == 0) {
            new MaterialDialog.Builder(this)
                    .title(R.string.introduction_title)
                    .content(R.string.introduction_content)
                    .onAny((dialog, which) -> {
                        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                        startActivity(intent);
                    }).show();
        }
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

    private void setInitState() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit().putBoolean(INIT_STATE, false).commit();
    }

    private boolean getInitState() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getBoolean(INIT_STATE, true);
    }
}