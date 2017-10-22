package com.example.fpt.busstation.ui.behaviorbottom.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.fpt.busstation.R;
import com.example.fpt.busstation.ui.base.BaseFragment;
import com.example.fpt.busstation.ui.base.BaseRcvAdapter;
import com.example.fpt.busstation.ui.behaviorbottom.adapter.StationAdapter;
import com.example.fpt.busstation.ui.behaviorbottom.dto.StationDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vi Nguyen on 17/10/2017.
 */

public class StationFragment extends BaseFragment implements BaseRcvAdapter.OnItemListener {
    RecyclerView recyclerView;
    StationAdapter stationAdapter;

    @Override
    public void onItemClick(View view, Object data, int position) {
        Log.d("OnItemClick", ((StationDto) data).getStationName());
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.rcv_station_fragment;
    }

    @Override
    protected void onInit(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rcv_station);
        List<StationDto> stationDtoList = new ArrayList<StationDto>();
        stationDtoList.add(new StationDto("Tram Ben Thanh", "Ben Thanh, Quan 1"));
        stationDtoList.add(new StationDto("Tram Hau Giang", "1076 Hau Giang, Quan 6"));
        stationDtoList.add(new StationDto("Tram Trung Son", "98 Trung Son, Quan 7"));
        stationDtoList.add(new StationDto("Tram Ben Thanh", "Ben Thanh, Quan 1"));
        stationDtoList.add(new StationDto("Tram Hau Giang", "1076 Hau Giang, Quan 6"));
        stationDtoList.add(new StationDto("Tram Trung Son", "98 Trung Son, Quan 7"));
        stationDtoList.add(new StationDto("Tram Ben Thanh", "Ben Thanh, Quan 1"));
        stationDtoList.add(new StationDto("Tram Hau Giang", "1076 Hau Giang, Quan 6"));
        stationDtoList.add(new StationDto("Tram Trung Son", "98 Trung Son, Quan 7"));
        stationDtoList.add(new StationDto("Tram Ben Thanh", "Ben Thanh, Quan 1"));
        stationDtoList.add(new StationDto("Tram Hau Giang", "1076 Hau Giang, Quan 6"));
        stationDtoList.add(new StationDto("Tram Trung Son", "98 Trung Son, Quan 7"));
        stationDtoList.add(new StationDto("Tram Ben Thanh", "Ben Thanh, Quan 1"));
        stationDtoList.add(new StationDto("Tram Hau Giang", "1076 Hau Giang, Quan 6"));
        stationDtoList.add(new StationDto("Tram Trung Son", "98 Trung Son, Quan 7"));
        stationDtoList.add(new StationDto("Tram Ben Thanh", "Ben Thanh, Quan 1"));
        stationDtoList.add(new StationDto("Tram Hau Giang", "1076 Hau Giang, Quan 6"));
        stationDtoList.add(new StationDto("Tram Trung Son", "98 Trung Son, Quan 7"));
        stationDtoList.add(new StationDto("Tram Ben Thanh", "Ben Thanh, Quan 1"));
        stationDtoList.add(new StationDto("Tram Hau Giang", "1076 Hau Giang, Quan 6"));
        stationDtoList.add(new StationDto("Tram Trung Son", "98 Trung Son, Quan 7"));
        stationAdapter = new StationAdapter(stationDtoList, this.getContext());
        stationAdapter.setmListener(this);
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(stationAdapter);
        recyclerView.setNestedScrollingEnabled(true);
    }
}
