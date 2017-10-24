package com.example.fpt.busstation.data.conn.response;

import android.util.Log;

import com.example.fpt.busstation.data.conn.ApiContansts;
import com.example.fpt.busstation.ui.behaviorbottom.dto.BusRouteInstructionDto;
import com.example.fpt.busstation.ui.behaviorbottom.dto.CoordDto;
import com.example.fpt.busstation.ui.behaviorbottom.dto.RecommendRoutesDto;
import com.example.fpt.busstation.ui.behaviorbottom.dto.RouteDto;
import com.example.fpt.busstation.ui.behaviorbottom.dto.WalkInstructionDto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuong on 10/23/2017.
 */

public class RouteResponse {
    public static final String TAG = RouteResponse.class.getSimpleName();

    private static List<RecommendRoutesDto> recommendRoutesDtoList;

    public static List<RecommendRoutesDto> convertData(String datas){
        if(datas!=null){
            recommendRoutesDtoList = new ArrayList<>();
            JSONArray recommendRoutes = null;
            try{
                recommendRoutes = new JSONArray(datas);
                if(recommendRoutes.length()>0){


                    for(int i = 0 ;i<recommendRoutes.length();i++){
                        JSONObject recommendRoute =   recommendRoutes.getJSONObject(i);
                        RecommendRoutesDto recommendRoutesDto = new RecommendRoutesDto();
                        recommendRoutesDto.setDistance(recommendRoute.getDouble(ApiContansts.KEY_DISTANCE));
                        recommendRoutesDto.setDuration(recommendRoute.getDouble(ApiContansts.KEY_DURATION));

                        recommendRoutesDto.setListBusNo(recommendRoute.getString(ApiContansts.KEY_LISTBUS));

                        String[] listBusNo  = recommendRoute.getString(ApiContansts.KEY_LISTBUS).split(",");
                        Log.d("listBusNoArray",listBusNo[0]);
                        recommendRoutesDto.setTotalBus(recommendRoute.getInt(ApiContansts.KEY_TOTALBUS));
                        recommendRoutesDto.setRecommendRouteId(recommendRoutesDto.generateRouteName());

                        List<Object> listInstruction = new ArrayList<>();
                        JSONArray arrayInstructions = recommendRoute.getJSONArray(ApiContansts.KEY_INSTRUCTION);
                        if(arrayInstructions.length()>0){
                            int count = 0;
                            for(int j = 0 ; j<arrayInstructions.length();j++){
                                JSONObject jsonInstruction = arrayInstructions.getJSONObject(j);
                                int typeIntruction = jsonInstruction.getInt(ApiContansts.KEY_TYPE);
                                switch (typeIntruction){
                                    case 1:
                                        WalkInstructionDto walk = new WalkInstructionDto();
                                        walk.setType(typeIntruction);
                                        walk.setBeginType(jsonInstruction.getInt(ApiContansts.KEY_BEGINTYPE));
                                        walk.setEndType(jsonInstruction.getInt(ApiContansts.KEY_ENDTYPE));
                                        walk.setDuration(jsonInstruction.getDouble(ApiContansts.KEY_DURATION));
                                        walk.setDistance(jsonInstruction.getDouble(ApiContansts.KEY_DISTANCE));
                                        if(count<listBusNo.length)
                                        walk.setToBus(Integer.parseInt(listBusNo[count].trim()));
                                        count++;
                                        CoordDto bCoord = new CoordDto();
                                        JSONObject beginCoord = jsonInstruction.getJSONObject(ApiContansts.KEY_BEGINCOORD);
                                        bCoord.setLat(beginCoord.getDouble(ApiContansts.KEY_LAT));
                                        bCoord.setLng(beginCoord.getDouble(ApiContansts.KEY_LNG));
                                        bCoord.setName(beginCoord.getString(ApiContansts.KEY_NAME));
                                        walk.setBeginCoord(bCoord);
                                        CoordDto eCoord = new CoordDto();
                                        JSONObject endCoord = jsonInstruction.getJSONObject(ApiContansts.KEY_ENDCOORD);
                                        eCoord.setLat(endCoord.getDouble(ApiContansts.KEY_LAT));
                                        eCoord.setLng(endCoord.getDouble(ApiContansts.KEY_LNG));
                                        eCoord.setName(endCoord.getString(ApiContansts.KEY_NAME));
                                        walk.setEndCoord(eCoord);
                                        listInstruction.add(walk);
                                        break;
                                    case 2:
                                        List<RouteDto> listRoute = new ArrayList<>();
                                        JSONArray arrayRoute = jsonInstruction.getJSONArray(ApiContansts.KEY_ROUTES);
                                        if(arrayRoute.length()>0){
                                            for(int k = 0;k<arrayRoute.length();k++){
                                                JSONObject jsonRoute = arrayRoute.getJSONObject(k);
                                                RouteDto routeDto = new RouteDto();
                                                routeDto.setLat(jsonRoute.getDouble(ApiContansts.KEY_LAT));
                                                routeDto.setLng(jsonRoute.getDouble(ApiContansts.KEY_LNG));
                                                routeDto.setName(jsonRoute.getString(ApiContansts.KEY_NAME));
                                                routeDto.setType(jsonRoute.getInt(ApiContansts.KEY_TYPE));
                                                listRoute.add(routeDto);
                                            }
                                        }
                                        BusRouteInstructionDto bus = new BusRouteInstructionDto();
                                        bus.setType(typeIntruction);
                                        bus.setColor(jsonInstruction.getString(ApiContansts.KEY_COLOR));
                                        bus.setBusNum(jsonInstruction.getInt(ApiContansts.KEY_BUSNUM));
                                        bus.setDistance(jsonInstruction.getDouble(ApiContansts.KEY_DISTANCE));
                                        bus.setDuration(jsonInstruction.getDouble(ApiContansts.KEY_DURATION));
                                        bus.setRouteDto(listRoute);
                                        listInstruction.add(bus);
                                        break;
                                }
                            }
                        }
                        recommendRoutesDto.setInstruction(listInstruction);
                        recommendRoutesDtoList.add(recommendRoutesDto);
                    }
                }
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }
        return recommendRoutesDtoList;
    }
}