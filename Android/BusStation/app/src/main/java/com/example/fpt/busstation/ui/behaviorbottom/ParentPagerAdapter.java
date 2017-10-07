package com.example.fpt.busstation.ui.behaviorbottom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.fpt.busstation.ui.behaviorbottom.TestFragment.TestFirstFragment;
import com.example.fpt.busstation.ui.behaviorbottom.TestFragment.TestSecondFragment;

/**
 * Created by cuong on 10/5/2017.
 */

public class ParentPagerAdapter extends FragmentStatePagerAdapter {
    private int mTabCount;

    public ParentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mTabCount = 0;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TestFirstFragment();
            case 1:
                return new TestSecondFragment();
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
