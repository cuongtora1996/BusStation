package com.example.fpt.busstation.ui.behaviorbottom.dto;

/**
 * Created by Vi Nguyen on 17/10/2017.
 */

public class BusDto {
    private String busNumber;
    private String busRoute;

    public BusDto(String busNumber, String busRoute) {
        this.busNumber = busNumber;
        this.busRoute = busRoute;
    }

    public BusDto() {
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getBusRoute() {
        return busRoute;
    }

    public void setBusRoute(String busRoute) {
        this.busRoute = busRoute;
    }
}
