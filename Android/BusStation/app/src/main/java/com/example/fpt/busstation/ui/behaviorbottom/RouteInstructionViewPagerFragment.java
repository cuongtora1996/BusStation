package com.example.fpt.busstation.ui.behaviorbottom;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.fpt.busstation.R;
import com.example.fpt.busstation.ui.base.BaseFragment;
import com.example.fpt.busstation.ui.behaviorbottom.dto.BusRouteInstructionDto;
import com.example.fpt.busstation.ui.behaviorbottom.dto.CoordDto;
import com.example.fpt.busstation.ui.behaviorbottom.dto.RecommendRoutesDto;
import com.example.fpt.busstation.ui.behaviorbottom.dto.RouteDto;
import com.example.fpt.busstation.ui.behaviorbottom.dto.WalkInstructionDto;
import com.example.fpt.busstation.ui.behaviorbottom.fragments.InstructionFragment;
import com.example.fpt.busstation.ui.behaviorbottom.fragments.RecommendRoutesFragment;
import com.example.fpt.busstation.util.BottomSheetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuong on 10/5/2017.
 */

public class RouteInstructionViewPagerFragment extends BaseFragment implements RecommendRoutesFragment.Callback {
    ViewPager viewPager;
    RouteInstructionPagerAdapter pagerAdapter;
    TabLayout tabLayout;
    RecommendRoutesFragment recommendRoutesFragment;
    InstructionFragment instructionFragment;
    List<RecommendRoutesDto> recommendRoutes;
    List<Object> routeInstructions;

    public List<RecommendRoutesDto> getRecommendRoutes() {
        return recommendRoutes;
    }

    public void setRecommendRoutes(List<RecommendRoutesDto> recommendRoutes) {
        this.recommendRoutes = recommendRoutes;
    }

    public List<Object> getRouteInstructions() {
        return routeInstructions;
    }

    public void setRouteInstructions(List<Object> routeInstructions) {
        this.routeInstructions = routeInstructions;
    }


    public RouteInstructionViewPagerFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.parent_viewpager_route_instruction;
    }

    @Override
    protected void onInit(View view) {
        generateListOffline();
        viewPager = (ViewPager) view.findViewById(R.id.vpRouteInstruction);
        tabLayout = (TabLayout) view.findViewById(R.id.tlRouteInstruction);
        //Cường
        recommendRoutesFragment = new RecommendRoutesFragment();
        recommendRoutesFragment.setList(getRecommendRoutes());
        recommendRoutesFragment.setCallback(this);

        instructionFragment = new InstructionFragment();
        instructionFragment.setListDto((List<Object>) getRouteInstructions().get(0));

        pagerAdapter = new RouteInstructionPagerAdapter(getChildFragmentManager(), recommendRoutesFragment, instructionFragment);
        pagerAdapter.setCount(2);
        //Vi
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_location));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_compass));

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

    //Cường
    private void generateListOffline() {
        List<RecommendRoutesDto> list = new ArrayList<>();
        list.add(new RecommendRoutesDto("Tuyen duong 18-24", 112, 90, 2, "18, 24"));
        list.add(new RecommendRoutesDto("Tuyen duong 55-18", 112, 90, 2, "55, 18"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-24", 112, 90, 2, "18, 24"));
        list.add(new RecommendRoutesDto("Tuyen duong 01-19-20", 112, 90, 3, "01, 19, 20"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-04-25", 112, 90, 3, "18, 04, 25"));
        list.add(new RecommendRoutesDto("Tuyen duong 139-32", 112, 90, 2, "139, 32"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-24", 112, 90, 2, "18, 24"));
        list.add(new RecommendRoutesDto("Tuyen duong 55-18", 112, 90, 2, "55, 18"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-24", 112, 90, 2, "18, 24"));
        list.add(new RecommendRoutesDto("Tuyen duong 01-19-20", 112, 90, 3, "01, 19, 20"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-04-25", 112, 90, 3, "18, 04, 25"));
        list.add(new RecommendRoutesDto("Tuyen duong 139-32", 112, 90, 2, "139, 32"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-24", 112, 90, 2, "18, 24"));
        list.add(new RecommendRoutesDto("Tuyen duong 55-18", 112, 90, 2, "55, 18"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-24", 112, 90, 2, "18, 24"));
        list.add(new RecommendRoutesDto("Tuyen duong 01-19-20", 112, 90, 3, "01, 19, 20"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-04-25", 112, 90, 3, "18, 04, 25"));
        list.add(new RecommendRoutesDto("Tuyen duong 139-32", 112, 90, 2, "139, 32"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-24", 112, 90, 2, "18, 24"));
        list.add(new RecommendRoutesDto("Tuyen duong 55-18", 112, 90, 2, "55, 18"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-24", 112, 90, 2, "18, 24"));
        list.add(new RecommendRoutesDto("Tuyen duong 01-19-20", 112, 90, 3, "01, 19, 20"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-04-25", 112, 90, 3, "18, 04, 25"));
        list.add(new RecommendRoutesDto("Tuyen duong 139-32", 112, 90, 2, "139, 32"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-24", 112, 90, 2, "18, 24"));
        list.add(new RecommendRoutesDto("Tuyen duong 55-18", 112, 90, 2, "55, 18"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-24", 112, 90, 2, "18, 24"));
        list.add(new RecommendRoutesDto("Tuyen duong 01-19-20", 112, 90, 3, "01, 19, 20"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-04-25", 112, 90, 3, "18, 04, 25"));
        list.add(new RecommendRoutesDto("Tuyen duong 139-32", 112, 90, 2, "139, 32"));
//        Vi
        setRecommendRoutes(list);
        List<Object> listInstructionDto = new ArrayList<>();
        List<Object> listDto = new ArrayList<>();
        listDto.add(new WalkInstructionDto(1, 1, 2,
                new CoordDto(10.768116, 106.688573, "FPT University"),
                new CoordDto(10.767676, 106.689362, "Station 1"),
                12, 200, 18));
        List<RouteDto> listRoute = new ArrayList<RouteDto>();
        listRoute.add(new RouteDto(1, 10.767676, 106.689362, "Station 1"));
        listRoute.add(new RouteDto(1, 10.76767, 106.690941, "Station 2"));
        listRoute.add(new RouteDto(2, 10.76767635, 106.68936157, ""));
        listRoute.add(new RouteDto(1, 10.768788, 106.693699, "Station 3"));
        listDto.add(new BusRouteInstructionDto(2, listRoute, "#41ba34", 18, 30, 10000));
        listDto.add(new WalkInstructionDto(1, 2, 3,
                new CoordDto(10.769504, 106.695587, "Station 4"),
                new CoordDto(10.770937, 106.697164, "My Home"),
                9, 150));
        List<Object> listDto1 = new ArrayList<>();
        listDto1.add(new WalkInstructionDto(1, 1, 2,
                new CoordDto(10.768116, 106.688573, "Nhà Cường"),
                new CoordDto(10.767676, 106.689362, "Station 1"),
                12, 200, 18));
        List<RouteDto> listRoute1 = new ArrayList<RouteDto>();
        listRoute1.add(new RouteDto(1, 10.767676, 106.689362, "Station 1"));
        listRoute1.add(new RouteDto(1, 10.76767, 106.690941, "Station 2"));
        listRoute1.add(new RouteDto(2, 10.76767635, 106.68936157, ""));
        listRoute1.add(new RouteDto(1, 10.768788, 106.693699, "Station 3"));
        listDto1.add(new BusRouteInstructionDto(2, listRoute, "#41ba34", 18, 30, 10000));
        listDto1.add(new WalkInstructionDto(1, 2, 3,
                new CoordDto(10.769504, 106.695587, "Station 4"),
                new CoordDto(10.770937, 106.697164, "FPT University"),
                9, 150));
        listInstructionDto.add(listDto);
        listInstructionDto.add(listDto1);
        setRouteInstructions(listInstructionDto);
    }

    @Override
    public void changeInstruction(int position) {
        instructionFragment.changeInstruction((List<Object>) getRouteInstructions().get(position));
        viewPager.setCurrentItem(1);
    }
}
