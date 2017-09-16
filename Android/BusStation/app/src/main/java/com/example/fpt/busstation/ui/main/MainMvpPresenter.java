package com.example.fpt.busstation.ui.main;

import com.example.fpt.busstation.ui.base.BaseMvpPresenter;

/**
 * Created by cuong on 9/13/2017.
 */

public interface MainMvpPresenter<T extends MainMvpView> extends BaseMvpPresenter<T> {
    void startRecordAudio();
    void stopRecordAudio();
}
