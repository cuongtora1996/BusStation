package com.example.fpt.busstation.ui.behaviorbottom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.fpt.busstation.ui.behaviorbottom.fragments.BusFragment;
import com.example.fpt.busstation.ui.behaviorbottom.fragments.StationFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vi Nguyen on 23/10/2017.
 */

public class BusStationPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;
//    private StationFragment stationFragment;
//    private BusFragment busFragment;
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();

//
//    public BusStationPagerAdapter(FragmentManager fm, StationFragment stationFragment, BusFragment busFragment) {
//        super(fm);
//        this.tabCount = 0;
//        this.stationFragment = stationFragment;
//        this.busFragment = busFragment;
//    }

    public BusStationPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    public void setTabCount(int tabCount) {
        this.tabCount = tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }

    public void addFragment(Fragment fragment, String tabTitle) {
        fragmentList.add(fragment);
        fragmentTitleList.add(tabTitle);
    }
}
