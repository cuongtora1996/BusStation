package com.example.fpt.busstation.ui.main;

import com.example.fpt.busstation.ui.base.BaseMvpView;

/**
 * Created by cuong on 9/13/2017.
 */

public interface MainMvpView extends BaseMvpView {
    void placeStation(double lng, double lat, String address);
    void showBottomSheet();
}
