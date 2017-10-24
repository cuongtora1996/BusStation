package com.example.fpt.busstation.ui.behaviorbottom;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.fpt.busstation.R;
import com.example.fpt.busstation.ui.base.BaseFragment;
import com.example.fpt.busstation.ui.behaviorbottom.dto.BusDto;
import com.example.fpt.busstation.ui.behaviorbottom.dto.StationDto;
import com.example.fpt.busstation.ui.behaviorbottom.fragments.BusFragment;
import com.example.fpt.busstation.ui.behaviorbottom.fragments.StationFragment;
import com.example.fpt.busstation.util.BottomSheetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vi Nguyen on 23/10/2017.
 */

public class BusStationViewPagerFragment extends BaseFragment implements StationFragment.Callback {
    ViewPager viewPager;
    BusStationPagerAdapter pagerAdapter;
    TabLayout tabLayout;
    BusFragment busFragment;
    StationFragment stationFragment;

    List<StationDto> stationDtoList;

    public BusStationViewPagerFragment() {
    }

    public BusStationViewPagerFragment(List<StationDto> list) {
        stationDtoList = list;
    }

    public List<StationDto> getStationDtoList() {
        return stationDtoList;
    }

    public void setStationDtoList(List<StationDto> stationDtoList) {
        this.stationDtoList = stationDtoList;
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.parent_viewpager_bus_station;
    }

    @Override
    protected void onInit(View view) {

        viewPager = (ViewPager) view.findViewById(R.id.vpBusStation);
        tabLayout = (TabLayout) view.findViewById(R.id.tlBusStation);

        stationFragment = new StationFragment();
        stationFragment.setStationDtoList(getStationDtoList());
        stationFragment.setCallback(this);


        busFragment = new BusFragment();
        busFragment.setBusDtoList(getStationDtoList().get(0).getListBus());

        pagerAdapter = new BusStationPagerAdapter(getChildFragmentManager(), stationFragment, busFragment);
        pagerAdapter.setTabCount(2);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_station));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_bus));

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



    @Override
    public void changeListBusCross(int position) {
        busFragment.changeBusCross(getStationDtoList().get(position).getListBus());
        viewPager.setCurrentItem(1);
    }
}
