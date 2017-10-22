package com.example.fpt.busstation.ui.behaviorbottom.dto;

/**
 * Created by Vi Nguyen on 17/10/2017.
 */

public class StationDto {
    private String stationName;
    private String stationAddress;

    public StationDto(String stationName, String stationAddress) {
        this.stationName = stationName;
        this.stationAddress = stationAddress;
    }

    public StationDto() {
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
