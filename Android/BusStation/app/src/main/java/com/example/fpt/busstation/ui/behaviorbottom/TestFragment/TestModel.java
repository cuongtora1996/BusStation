package com.example.fpt.busstation.ui.behaviorbottom.TestFragment;

/**
 * Created by cuong on 9/29/2017.
 */

public class TestModel {
    private String stationName,stationAddress;

    public TestModel() {
    }

    public TestModel(String stationName, String stationAddress) {
        this.stationName = stationName;
        this.stationAddress = stationAddress;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }
}
