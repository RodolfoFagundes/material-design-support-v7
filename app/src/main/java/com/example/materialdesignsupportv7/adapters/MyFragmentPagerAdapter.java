package com.example.materialdesignsupportv7.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.materialdesignsupportv7.fragments.AFragment;
import com.example.materialdesignsupportv7.fragments.BFragment;
import com.example.materialdesignsupportv7.fragments.CarCardFragment;
import com.example.materialdesignsupportv7.fragments.CarFragment;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTabTitles;

    public MyFragmentPagerAdapter(FragmentManager fm, String[] tabTitles) {
        super(fm);
        this.mTabTitles = tabTitles;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new AFragment();
            case 1:
                return new BFragment();
            case 2:
                return new CarFragment();
            case 3:
                return new CarCardFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.mTabTitles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return this.mTabTitles[position];
    }
}
