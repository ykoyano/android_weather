package com.example.user.weather.adapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class DynamicFragmentPagerAdapter extends PagerAdapter {

    private Fragment _primaryItem;
    private List<FragmentInfo> _fragments = new ArrayList<>();
    private FragmentManager _fm;
    private FragmentTransactionProxy _ftp;
    private boolean _isNeedAllChange;

    protected static class FragmentInfo {

        private WeakReference<Fragment> fragment;
        private CharSequence name;
        private boolean isShown;

        public FragmentInfo(CharSequence name, Fragment fragment) {
            this.name = name;
            this.fragment = new WeakReference<>(fragment);
        }

        public CharSequence getName() {
            return this.name;
        }

        public void setName(CharSequence name) {
            this.name = name;
        }

        public Fragment getFragment() {
            return this.fragment.get();
        }

        public void setFragment(Fragment fragment) {
            if (this.fragment != null)
                this.fragment.clear();
            this.fragment = new WeakReference<>(fragment);
        }

        public boolean isShown() {
            return isShown;
        }

        public void setShown(boolean isShown) {
            this.isShown = isShown;
        }

        @Override
        public String toString() {
            return (String) this.name;
        }
    }

    protected final class FragmentTransactionProxy {

        private FragmentTransaction _ft;

        public FragmentTransactionProxy(FragmentTransaction ft) {
            _ft = ft;
        }

        public FragmentTransactionProxy attach(Fragment fragment) {
            _ft.attach(fragment);
            return this;
        }

        public FragmentTransactionProxy detach(Fragment fragment) {
            _ft.detach(fragment);
            return this;
        }

        public FragmentTransactionProxy hide(Fragment fragment) {
            _ft.hide(fragment);
            return this;
        }

        public boolean isEmpty() {
            return _ft.isEmpty();
        }

        public FragmentTransactionProxy remove(Fragment fragment) {
            _ft.remove(fragment);
            return this;
        }

        public FragmentTransactionProxy show(Fragment fragment) {
            _ft.show(fragment);
            return this;
        }

        private FragmentTransaction getTransaction() {
            return _ft;
        }

    }

    public DynamicFragmentPagerAdapter(FragmentManager fm) {
        _fm = fm;
    }

    public DynamicFragmentPagerAdapter(FragmentManager fm, Map<CharSequence, Fragment> fragments) {
        _fm = fm;

        for (Entry<CharSequence, Fragment> entry : fragments.entrySet()) {
            _fragments.add(new FragmentInfo(entry.getKey(), entry.getValue()));
        }
    }

    @Override
    public void startUpdate(ViewGroup container) {
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return _fragments.get(position).getName();
    }

    @Override
    public int getCount() {
        return _fragments.size();
    }

    @Override
    public final Object instantiateItem(ViewGroup container, int position) {
        FragmentInfo fi = _fragments.get(position);
        Fragment registFragment = fi.getFragment();

        StringBuilder tag = new StringBuilder();
        tag.append(container.getId()).append(":").append(fi.getName());

        Fragment f = _fm.findFragmentByTag(tag.toString());
        transaction();

        if (registFragment.equals(f)) {
            _ftp.attach(f);
            return f;
        }

        if (!registFragment.equals(_primaryItem)) {
            registFragment.setMenuVisibility(false);
            registFragment.setUserVisibleHint(false);
        }

        fi.setShown(true);
        _ftp.getTransaction().add(container.getId(), registFragment, tag.toString());

        return registFragment;
    }

    @Override
    public final void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = (Fragment) object;
        transaction();
        _ftp.detach(fragment);
    }

    @Override
    public final void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (object == null)
            return;

        Fragment fragment = (Fragment) object;

        if (!fragment.equals(_primaryItem)) {

            if (_primaryItem != null) {
                _primaryItem.setMenuVisibility(false);
                _primaryItem.setUserVisibleHint(false);
            }

            fragment.setMenuVisibility(true);
            fragment.setUserVisibleHint(true);
            _primaryItem = fragment;
        }
    }

    @Override
    public final void finishUpdate(ViewGroup container) {
        if (_ftp != null) {
            _ftp.getTransaction().commitAllowingStateLoss();
            _ftp = null;
            _fm.executePendingTransactions();
        }

        _isNeedAllChange = false;
    }

    @Override
    public final boolean isViewFromObject(View view, Object object) {
        return ((Fragment) object).getView() == view;
    }

    @Override
    public final int getItemPosition(Object object) {
        return _isNeedAllChange ? POSITION_NONE : POSITION_UNCHANGED;
    }

    public void add(CharSequence name, Fragment fragment) {
        if (hasName(name))
            throw new IllegalArgumentException();

        _fragments.add(new FragmentInfo(name, fragment));
    }

    public Fragment get(int position) {
        return _fragments.get(position).getFragment();
    }

    public void remove(int position) {
        transaction();
        _ftp.remove(_fragments.get(position).getFragment());
        _fragments.remove(position);
        _isNeedAllChange = true;
    }

    public void replace(int position, CharSequence name, Fragment fragment) {
        if (hasName(name, position))
            throw new IllegalArgumentException();

        FragmentInfo fi = _fragments.get(position);
        if (fi.isShown()) {
            transaction();
            _ftp.remove(fi.getFragment());
            _isNeedAllChange = true;
        }

        _fragments.set(position, new FragmentInfo(name, fragment));
    }

    public void insert(int position, CharSequence name, Fragment fragment) {
        if (hasName(name))
            throw new IllegalArgumentException();
        _fragments.add(position, new FragmentInfo(name, fragment));
        _isNeedAllChange = true;
    }

    protected final FragmentTransactionProxy getFragmentTransactionProxy() {
        transaction();
        return _ftp;
    }

    protected final void needAllChange() {
        _isNeedAllChange = true;
    }

    protected final FragmentInfo getFragmentInfo(int position) {
        return _fragments.get(position);
    }

    protected final List<FragmentInfo> getFragmentInfoes() {
        return _fragments;
    }

    protected final boolean hasName(CharSequence name) {
        for (FragmentInfo fi : _fragments) {
            if (fi.getName().equals(name))
                return true;
        }
        return false;
    }

    protected final boolean hasName(CharSequence name, int position) {
        for (int i = 0, size = _fragments.size(); i < size; i++) {
            if (_fragments.get(i).getName().equals(name)) {
                return i != position ? true : false;
            }
        }
        return false;
    }

    private void transaction() {
        if (_ftp == null)
            _ftp = new FragmentTransactionProxy(_fm.beginTransaction());
    }

}