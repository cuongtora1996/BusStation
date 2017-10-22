package com.example.fpt.busstation.ui.behaviorbottom.dto;

import java.util.List;

/**
 * Created by Vi Nguyen on 21/10/2017.
 */

public class BusRouteInstructionDto {
    private int type;
    private List<RouteDto> routeDto;
    private String color;
    private int busNum;
    private double duration;
    private double distance;

    public BusRouteInstructionDto() {
    }

    public BusRouteInstructionDto(int type, List<RouteDto> routeDto, String color, int busNum, double duration, double distance) {
        this.type = type;
        this.routeDto = routeDto;
        this.color = color;
        this.busNum = busNum;
        this.duration = duration;
        this.distance = distance;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<RouteDto> getRouteDto() {
        return routeDto;
    }

    public void setRouteDto(List<RouteDto> routeDto) {
        this.routeDto = routeDto;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getBusNum() {
        return busNum;
    }

    public void setBusNum(int busNum) {
        this.busNum = busNum;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}

