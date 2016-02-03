package com.example.user.weather.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;


public abstract class MyAdapter extends FragmentPagerAdapter {

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    public void destroyAllItem(ViewPager pager) {
        for (int i = 0; i < getCount(); i++) {
            try {
                Object obj = this.instantiateItem(pager, i);
                if (obj != null)
                    destroyItem(pager, i, obj);
            } catch (Exception e) {
            }
        }
    }
}