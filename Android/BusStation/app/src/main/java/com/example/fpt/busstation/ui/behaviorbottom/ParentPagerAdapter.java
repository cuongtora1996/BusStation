package com.example.fpt.busstation.ui.behaviorbottom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.fpt.busstation.ui.behaviorbottom.fragments.BusFragment;
import com.example.fpt.busstation.ui.behaviorbottom.fragments.InstructionFragment;
import com.example.fpt.busstation.ui.behaviorbottom.fragments.RecommendRoutesFragment;
import com.example.fpt.busstation.ui.behaviorbottom.fragments.StationFragment;

/**
 * Created by cuong on 10/5/2017.
 */

public class ParentPagerAdapter extends FragmentStatePagerAdapter {
    private int mTabCount;
    private RecommendRoutesFragment recommendRoutesFragment;
    private InstructionFragment instructionFragment;
    public ParentPagerAdapter(FragmentManager fragmentManager,RecommendRoutesFragment recommendRoutesFragment, InstructionFragment instructionFragment) {
        super(fragmentManager);
        this.mTabCount = 0;
        this.recommendRoutesFragment = recommendRoutesFragment;
        this.instructionFragment = instructionFragment;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
//                return new BusFragment();
                return recommendRoutesFragment;
            case 1:

//                return new StationFragment();
                return instructionFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabCount;

    }

    public void setCount(int mTabCount) {
        this.mTabCount = mTabCount;
    }

}
