package com.example.fpt.busstation.ui.behaviorbottom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.fpt.busstation.ui.behaviorbottom.fragments.BusFragment;
import com.example.fpt.busstation.ui.behaviorbottom.fragments.StationFragment;

/**
 * Created by Vi Nguyen on 23/10/2017.
 */

public class BusStationPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;
    private StationFragment stationFragment;
    private BusFragment busFragment;


    public BusStationPagerAdapter(FragmentManager fm, StationFragment stationFragment, BusFragment busFragment) {
        super(fm);
        this.tabCount = 0;
        this.stationFragment = stationFragment;
        this.busFragment = busFragment;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return stationFragment;
            case 1:
                return busFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    public void setTabCount(int tabCount) {
        this.tabCount = tabCount;
    }
}
