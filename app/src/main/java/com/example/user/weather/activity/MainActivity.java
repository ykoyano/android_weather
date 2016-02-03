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
import com.example.user.weather.model.GeoEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends ActivityBase implements ViewPager.OnPageChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String KEY_GEO = "geo";

    private ActivityMainBinding binding;

    private SubFragmentPagerAdapter adapter;

    List<GeoEntity> geos;
    List<String> geosString;

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

        HashMap<CharSequence, Fragment> fragments = new HashMap<>();
        for (GeoEntity geoEntity : geos) {
            fragments.put(geoEntity.getCity(), MainFragment.newInstance(geoEntity));
        }
//        SubFragmentPagerAdapter adapter = new SubFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        this.adapter = new SubFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.addOnPageChangeListener(this);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

    }

    //    @Override
    //    protected void onNewIntent(Intent intent) {
    //        if(intent.getStringExtra(KEY_GEO) != null){
    //            this.geosString.add(intent.getStringExtra(KEY_GEO));
    //        }
    //    }

    //    private FragmentPagerAdapter makeFragmentPagerAdapter() {
    //        return new FragmentPagerAdapter(getSupportFragmentManager()) {
    //            @Override
    //            public Fragment getItem(int position) {
    //                return MainFragment.newInstance(geosString.get(position));
    //            }
    //
    //            @Override
    //            public CharSequence getPageTitle(int position) {
    //                return geosString.get(position);
    //            }
    //
    //            @Override
    //            public int getCount() {
    //                return  geosString.size();
    //            }
    //        };
    //    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent.getSerializableExtra(KEY_GEO) != null) {
            if(binding.tabLayout.getTabCount() > this.geos.size()){
                binding.tabLayout.removeTabAt(this.geos.size());
            }
            GeoEntity geoEntity = (GeoEntity) intent.getSerializableExtra(KEY_GEO);
            this.adapter.add(geoEntity.getCity(), MainFragment.newInstance(geoEntity));
            this.adapter.notifyDataSetChanged();
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(geoEntity.getCity()));
        }
    }

//    private MyAdapter makeFragmentPagerAdapter() {
//        return new MyAdapter(getSupportFragmentManager()) {
//
//            @Override
//            public Fragment getItem(int position) {
//                return MainFragment.newInstance(geos.get(position));
//            }
//
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return geos.get(position).getCity();
//            }
//
//            @Override
//            public int getCount() {
//                return geos.size();
//            }
//
//            @Override
//            public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
//
//                if (position <= getCount()) {
//                    FragmentManager manager = ((Fragment) object).getFragmentManager();
//                    FragmentTransaction trans = manager.beginTransaction();
//                    trans.remove((Fragment) object);
//                    trans.commit();
//                }
//            }
//        };
//    }

    //    private FragmentPagerAdapter makeFragmentPagerAdapter() {
    //        return new FragmentPagerAdapter(getSupportFragmentManager()) {
    //            @Override
    //            public Fragment getItem(int position) {
    //                return MainFragment.newInstance(geos.get(position).getCity());
    //            }
    //
    //            @Override
    //            public CharSequence getPageTitle(int position) {
    //                return geos.get(position).getCity();
    //            }
    //
    //            @Override
    //            public int getCount() {
    //                return  geos.size();
    //            }
    //
    //            public void destroyAllItem(ViewPager pager) {
    //                for (int i = 0; i < getCount() - 1; i++) {
    //                    try {
    //                        Object obj = this.instantiateItem(pager, i);
    //                        if (obj != null)
    //                            destroyItem(pager, i, obj);
    //                    } catch (Exception e) {
    //                    }
    //                }
    //            }
    //
    //            @Override
    //            public void destroyItem(ViewGroup container, int position, Object object) {
    //                super.destroyItem(container, position, object);
    //
    //                if (position <= getCount()) {
    //                    FragmentManager manager = ((Fragment) object).getFragmentManager();
    //                    FragmentTransaction trans = manager.beginTransaction();
    //                    trans.remove((Fragment) object);
    //                    trans.commit();
    //                }
    //            }
    //        };
    //    }

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
        cities.add(new GeoEntity("さいたま", "110010", 139.569754, 35.8777));
        cities.add(new GeoEntity("栃木", "110010", 139.748078, 36.465153));
        return cities;
    }
}
