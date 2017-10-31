package com.example.fpt.busstation.ui.behaviorbottom;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fpt.busstation.R;
import com.example.fpt.busstation.ui.base.BaseFragment;
import com.example.fpt.busstation.ui.behaviorbottom.dto.StationDto;
import com.example.fpt.busstation.ui.behaviorbottom.fragments.BusFragment;
import com.example.fpt.busstation.ui.behaviorbottom.fragments.StationFragment;
import com.example.fpt.busstation.util.BottomSheetUtils;

import java.util.List;

/**
 * Created by Vi Nguyen on 23/10/2017.
 */

public class BusStationViewPagerFragment extends BaseFragment implements StationFragment.Callback {
    CustomViewPager viewPager;
    TabLayout tabLayout;
    BusFragment busFragment;
    StationFragment stationFragment;
    List<StationDto> stationDtoList;
    private static final int NUMBER_TAB = 2;
    private static final int[] tabIcons = {R.drawable.ic_tab_icon_station, R.drawable.ic_tab_icon_bus};

    public BusStationViewPagerFragment() {
    }

    public BusStationViewPagerFragment(List<StationDto> list) {
        stationDtoList = list;
    }

    public List<StationDto> getStationDtoList() {
        return stationDtoList;
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.parent_viewpager_bus_station;
    }

    @Override
    protected void onInit(View view) {
        stationFragment = new StationFragment();
        stationFragment.setStationDtoList(getStationDtoList());
        stationFragment.setCallback(this);

        busFragment = new BusFragment();
        busFragment.setBusDtoList(getStationDtoList().get(0).getListBus());

        viewPager = (CustomViewPager) view.findViewById(R.id.vpBusStation);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tlBusStation);
        tabLayout.setupWithViewPager(viewPager);
        setupTabAttribute(viewPager, tabLayout);

        viewPager.setOffscreenPageLimit(tabLayout.getTabCount());
        viewPager.setPagingEnabled(false);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        BottomSheetUtils.setupViewPager(viewPager, getBaseActivity().findViewById(R.id.bottom_sheet));
    }


    private void setupViewPager(ViewPager viewPager) {
        BusStationPagerAdapter adapter = new BusStationPagerAdapter(getChildFragmentManager());
        adapter.addFragment(stationFragment, "Trạm gần đây");
        adapter.addFragment(busFragment, "Xe ghé trạm");
        adapter.setTabCount(NUMBER_TAB);
        viewPager.setAdapter(adapter);
    }

    private void setupTabAttribute(final ViewPager viewPager, TabLayout tabLayout) {
        for (int i = 0; i < NUMBER_TAB; i++) {
            TextView tab = (TextView) LayoutInflater.from(this.getContext()).inflate(R.layout.custom_tab, null);

            tab.setText(viewPager.getAdapter().getPageTitle(i));
            tab.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[i], 0, 0);
            tabLayout.getTabAt(i).setCustomView(tab);
        }
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
        LinearLayout tabStrip = ((LinearLayout) tabLayout.getChildAt(0));


        tabStrip.getChildAt(1).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

    }

    @Override
    public void changeListBusCross(int position) {
        busFragment.changeBusCross(getStationDtoList().get(position).getListBus());
        viewPager.setCurrentItem(1);
    }
}
