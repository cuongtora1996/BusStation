package com.example.fpt.busstation.data.conn.request;

import android.util.Log;

import com.example.fpt.busstation.data.conn.RestClient;
import com.example.fpt.busstation.service.OnResponseStringListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cuong on 9/27/2017.
 */

public class IntentRequest {
    public static final String TAG = IntentRequest.class.getSimpleName();

    public static void sendGetRequest(String type,String lng, String lat,String dist, OnResponseStringListener listener) {
        Map<String,String> param= new HashMap<>();
        param.put("type",type);
        param.put("long",lng);
        param.put("lat",lat);
        param.put("dist",dist);
        Log.d(TAG, param.toString());
        RestClient.getInstance().getRequest("http://192.168.1.129:81/voicebus/api.php",param,listener);
    }
}
