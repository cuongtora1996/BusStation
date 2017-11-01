package com.example.fpt.busstation.data.conn.request;

import android.util.Log;

import com.example.fpt.busstation.data.conn.ApiContansts;
import com.example.fpt.busstation.data.conn.ApiURL;
import com.example.fpt.busstation.data.conn.RestClient;
import com.example.fpt.busstation.service.OnResponseStringListener;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cuong on 10/23/2017.
 */

public class RouteRequest {
    public static final String TAG = RouteRequest.class.getSimpleName();


    public static void sendGetRequest(Double lng, Double lat, String begin, String end, int type, OnResponseStringListener listener) {
        Map<String, String> param = new HashMap<>();
        //Cuong - first api
//        RestClient.getInstance().getRequest("https://api.myjson.com/bins/t87on",param,listener);
        //FIXME Vi - latest api
//        RestClient.getInstance().getRequest("https://api.myjson.com/bins/12uu5n",param,listener);
        RestClient.getInstance().getRequest("https://api.myjson.com/bins/bf7df", param, listener);


    }

}
