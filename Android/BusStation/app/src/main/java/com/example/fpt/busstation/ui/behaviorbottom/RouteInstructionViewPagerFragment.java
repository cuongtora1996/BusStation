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


    public List<RecommendRoutesDto> getRecommendRoutes() {
        return recommendRoutes;
    }

    public void setRecommendRoutes(List<RecommendRoutesDto> recommendRoutes) {
        this.recommendRoutes = recommendRoutes;
    }




    public RouteInstructionViewPagerFragment() {
        // Required empty public constructor
    }
    public RouteInstructionViewPagerFragment(List<RecommendRoutesDto> list) {
        // Required empty public constructor
        this.recommendRoutes = list;
    }
    @Override
    protected int getContentViewResource() {
        return R.layout.parent_viewpager_route_instruction;
    }

    @Override
    protected void onInit(View view) {

        viewPager = (ViewPager) view.findViewById(R.id.vpRouteInstruction);
        tabLayout = (TabLayout) view.findViewById(R.id.tlRouteInstruction);
        //Cường
        recommendRoutesFragment = new RecommendRoutesFragment();
        recommendRoutesFragment.setList(getRecommendRoutes());
        recommendRoutesFragment.setCallback(this);

        instructionFragment = new InstructionFragment();
        instructionFragment.setListDto(getRecommendRoutes().get(0).getInstruction());

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
    @Override
    public void changeInstruction(int position) {
        instructionFragment.changeInstruction(getRecommendRoutes().get(position).getInstruction());
        viewPager.setCurrentItem(1);
    }
}
