package com.example.fpt.busstation.data.conn.request;

import android.util.Log;

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

    public static void sendGetRequest(Double lng, Double lat, String begin, String end, int type,OnResponseStringListener listener) {
        Map<String,String> param= new HashMap<>();
//        param.put("type",type+"");
//        if(type==5)
//            param.put("begin",begin);
//        else{
//            param.put("long",lng+"");
//            param.put("lat",lat+"");
//        }
//        param.put("end",end);
        RestClient.getInstance().getRequest("https://api.myjson.com/bins/t87on",param,listener);
    }

}
