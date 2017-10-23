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
    List<List<BusDto>> busDtoList;
    List<StationDto> stationDtoList;

    public BusStationViewPagerFragment() {
    }

    public List<List<BusDto>> getBusDtoList() {
        return busDtoList;
    }

    public void setBusDtoList(List<List<BusDto>> busDtoList) {
        this.busDtoList = busDtoList;
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
        generateListOffline();
        viewPager = (ViewPager) view.findViewById(R.id.vpBusStation);
        tabLayout = (TabLayout) view.findViewById(R.id.tlBusStation);

        stationFragment = new StationFragment();
        stationFragment.setStationDtoList(getStationDtoList());
        stationFragment.setCallback(this);


        busFragment = new BusFragment();
        busFragment.setBusDtoList(getBusDtoList().get(0));

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

    private void generateListOffline() {
        List<BusDto> busCrossStation1 = new ArrayList<BusDto>();
        busCrossStation1.add(new BusDto("Tuyen so 01", "Ben Thanh - BX Cho Lon"));
        busCrossStation1.add(new BusDto("Tuyen so 02", "Ben Thanh - BX Mien Tay"));
        busCrossStation1.add(new BusDto("Tuyen so 03", "Ben Thanh - BX Thanh Loc"));


        List<BusDto> busCrossStation2 = new ArrayList<BusDto>();
        busCrossStation2.add(new BusDto("Tuyen so 55", "Quang Trung - Hoc Mon"));
        busCrossStation2.add(new BusDto("Tuyen so 18", "Quang Trung - BX Mien Dong"));
        busCrossStation2.add(new BusDto("Tuyen so 24", "Quang Trung - Tan Binh"));
        busCrossStation2.add(new BusDto("Tuyen so 44", "Quang Trung - Nguyen Kiem"));

        List<List<BusDto>> busList = new ArrayList<>();
        busList.add(busCrossStation1);
        busList.add(busCrossStation2);

        setBusDtoList(busList);

        List<StationDto> list1 = new ArrayList<StationDto>();
        list1.add(new StationDto("Tram Ben Thanh", "Ben Thanh, Quan 1"));
        list1.add(new StationDto("Tram Quang Trung", "01 Quang Trung, Quan Go Vap"));
        List<Object> list = new ArrayList<>();
        setStationDtoList(list1);
    }

    @Override
    public void changeListBusCross(int position) {
        busFragment.changeBusCross((List<BusDto>)getBusDtoList().get(position));
        viewPager.setCurrentItem(1);
    }
}
