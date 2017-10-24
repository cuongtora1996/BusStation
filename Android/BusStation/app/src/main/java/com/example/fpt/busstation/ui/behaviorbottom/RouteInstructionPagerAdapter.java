package com.example.fpt.busstation.ui.behaviorbottom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.fpt.busstation.ui.behaviorbottom.fragments.BusFragment;
import com.example.fpt.busstation.ui.behaviorbottom.fragments.InstructionFragment;
import com.example.fpt.busstation.ui.behaviorbottom.fragments.RecommendRoutesFragment;
import com.example.fpt.busstation.ui.behaviorbottom.fragments.StationFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vi Nguyen on 24/10/2017.
 */

public class RouteInstructionPagerAdapter extends FragmentStatePagerAdapter {
    private int mTabCount;
    private RecommendRoutesFragment recommendRoutesFragment;
    private InstructionFragment instructionFragment;
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();


    public RouteInstructionPagerAdapter(FragmentManager fragmentManager, RecommendRoutesFragment recommendRoutesFragment, InstructionFragment instructionFragment) {
        super(fragmentManager);
        this.mTabCount = 0;
        this.recommendRoutesFragment = recommendRoutesFragment;
        this.instructionFragment = instructionFragment;
    }

    public RouteInstructionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mTabCount;

    }

    public void setCount(int mTabCount) {
        this.mTabCount = mTabCount;
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
