package com.example.fpt.busstation.ui.base;

/**
 * Created by cuong on 9/11/2017.
 */

public interface BaseMvpView {
    void showLoading();

    void hideLoading();

    void onError(String message);

    void showMessage(String message);

    boolean isNetworkConnected();

    boolean isServiceRunning(Class<?> serviceClass);


}
