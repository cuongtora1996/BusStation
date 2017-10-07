package com.example.fpt.busstation.ui.behaviorbottom;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.fpt.busstation.R;
import com.example.fpt.busstation.ui.base.BaseFragment;
import com.example.fpt.busstation.util.BottomSheetUtils;

/**
 * Created by cuong on 10/5/2017.
 */

public class ParentViewPagerFragment extends BaseFragment {
    ViewPager viewPager;
    ParentPagerAdapter pagerAdapter;
    TabLayout tabLayout;
    public ParentViewPagerFragment() {
        // Required empty public constructor
    }
    @Override
    protected int getContentViewResource() {
        return R.layout.fragment_parent_viewpager;
    }

    @Override
    protected void onInit(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.vpPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        pagerAdapter = new ParentPagerAdapter(getChildFragmentManager());
        pagerAdapter.setCount(2);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_bus));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_station));
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(tabLayout.getTabCount());

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        BottomSheetUtils.setupViewPager(viewPager, getBaseActivity().findViewById(R.id.bottom_sheet));

    }


}
