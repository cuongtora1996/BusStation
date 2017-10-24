package com.example.fpt.busstation.ui.main;

import android.location.Location;

import com.example.fpt.busstation.ui.base.BaseMvpPresenter;

/**
 * Created by cuong on 9/13/2017.
 */

public interface MainMvpPresenter<T extends MainMvpView> extends BaseMvpPresenter<T> {
    void startRecordAudio();
    void stopRecordAudio();
    void sendTTSRequest(String text);
    void sendRouteRequest(String text);
    void sendStationRequest(String text);
    void sendRequest(Location location);
}
