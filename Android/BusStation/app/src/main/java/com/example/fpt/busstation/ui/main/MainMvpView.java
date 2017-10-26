package com.example.fpt.busstation.ui.main;

import com.example.fpt.busstation.ui.base.BaseMvpView;
import com.example.fpt.busstation.ui.behaviorbottom.BusStationViewPagerFragment;
import com.example.fpt.busstation.ui.behaviorbottom.RouteInstructionViewPagerFragment;
import com.example.fpt.busstation.ui.behaviorbottom.dto.RecommendRoutesDto;
import com.example.fpt.busstation.ui.behaviorbottom.dto.StationDto;

import java.util.List;

/**
 * Created by cuong on 9/13/2017.
 */

public interface MainMvpView extends BaseMvpView {
    void placeStation(double lng, double lat, String address, String name, int position);
    void showBottomSheet();
    void hideBottomSheet();
    void showBusAndStation(List<StationDto> list);
    void showRouteInstruction(List<RecommendRoutesDto> list);
}
