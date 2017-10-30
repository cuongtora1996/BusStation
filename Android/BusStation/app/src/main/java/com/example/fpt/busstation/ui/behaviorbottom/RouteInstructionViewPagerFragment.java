package com.example.fpt.busstation.ui.behaviorbottom;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.fpt.busstation.R;
import com.example.fpt.busstation.ui.base.BaseFragment;
import com.example.fpt.busstation.ui.behaviorbottom.dto.RecommendRoutesDto;
import com.example.fpt.busstation.ui.behaviorbottom.fragments.InstructionFragment;
import com.example.fpt.busstation.ui.behaviorbottom.fragments.RecommendRoutesFragment;
import com.example.fpt.busstation.util.BottomSheetUtils;

import java.util.List;

/**
 * Created by Vi Nguyen on 24/10/2017.
 */

public class RouteInstructionViewPagerFragment extends BaseFragment implements RecommendRoutesFragment.Callback {
    CustomViewPager viewPager;
    RouteInstructionPagerAdapter pagerAdapter;
    TabLayout tabLayout;
    RecommendRoutesFragment recommendRoutesFragment;
    InstructionFragment instructionFragment;
    List<RecommendRoutesDto> recommendRoutes;

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    Callback callback;

    private static final int NUMBER_TAB = 2;
    private static final int[] tabIcons = {R.drawable.ic_tab_icon_route, R.drawable.ic_tab_icon_direction};


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
        recommendRoutesFragment = new RecommendRoutesFragment();
        recommendRoutesFragment.setList(getRecommendRoutes());
        recommendRoutesFragment.setCallback(this);

        instructionFragment = new InstructionFragment();
        instructionFragment.setListDto(getRecommendRoutes().get(0).getInstruction());

        viewPager = (CustomViewPager) view.findViewById(R.id.vpRouteInstruction);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tlRouteInstruction);
        tabLayout.setupWithViewPager(viewPager);
        setupTabAttribute(viewPager, tabLayout);

        viewPager.setOffscreenPageLimit(tabLayout.getTabCount());
        viewPager.setPagingEnabled(false);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        BottomSheetUtils.setupViewPager(viewPager, getBaseActivity().findViewById(R.id.bottom_sheet));
    }

    private void setupViewPager(ViewPager viewPager) {
        BusStationPagerAdapter adapter = new BusStationPagerAdapter(getChildFragmentManager());
        adapter.addFragment(recommendRoutesFragment, "Lộ trình");
        adapter.addFragment(instructionFragment, "Cách đi");
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
    }

    @Override
    public void changeInstruction(int position) {
        callback.drawRoute(getRecommendRoutes().get(position).getInstruction());
        instructionFragment.changeInstruction(getRecommendRoutes().get(position).getInstruction());
        viewPager.setCurrentItem(1);
    }

    public interface Callback {
        void drawRoute(List<Object> instruction);
    }
}
