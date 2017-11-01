package com.example.fpt.busstation.data.conn.request;

import com.example.fpt.busstation.data.conn.RestClient;
import com.example.fpt.busstation.service.OnResponseStringListener;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cuong on 10/24/2017.
 */

public class StationRequest {
    public static final String TAG = RouteRequest.class.getSimpleName();


    public static void sendGetRequest(Double lng, Double lat, String numbus,int type,OnResponseStringListener listener) {
        Map<String,String> param= new HashMap<>();
        //Cuong
//        RestClient.getInstance().getRequest("https://api.myjson.com/bins/1d6bjb",param,listener);
        //FIXME - Vi - updated json
        RestClient.getInstance().getRequest("https://api.myjson.com/bins/lw7wj",param,listener);
    }

}
