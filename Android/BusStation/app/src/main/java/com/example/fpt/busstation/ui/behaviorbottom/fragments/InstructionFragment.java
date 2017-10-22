package com.example.fpt.busstation.ui.behaviorbottom.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.fpt.busstation.R;
import com.example.fpt.busstation.ui.base.BaseFragment;
import com.example.fpt.busstation.ui.base.BaseRcvAdapter;
import com.example.fpt.busstation.ui.behaviorbottom.adapter.RouteInstructionAdapter;
import com.example.fpt.busstation.ui.behaviorbottom.dto.BusDto;
import com.example.fpt.busstation.ui.behaviorbottom.dto.BusRouteInstructionDto;
import com.example.fpt.busstation.ui.behaviorbottom.dto.CoordDto;
import com.example.fpt.busstation.ui.behaviorbottom.dto.RouteDto;
import com.example.fpt.busstation.ui.behaviorbottom.dto.WalkInstructionDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vi Nguyen on 21/10/2017.
 */

public class InstructionFragment extends BaseFragment implements BaseRcvAdapter.OnItemListener {
    RecyclerView recyclerView;
    RouteInstructionAdapter adapter;

    public InstructionFragment() {
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.rcv_route_instruction_fragment;
    }

    @Override
    protected void onInit(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rcv_instruction);
        List<Object> listDto = new ArrayList<>();
        listDto.add(new WalkInstructionDto(1, 1, 2,
                new CoordDto(10.768116, 106.688573, "FPT University"),
                new CoordDto(10.767676, 106.689362, "Station 1"),
                12, 200, 18));
        List<RouteDto> listRoute = new ArrayList<RouteDto>();
        listRoute.add(new RouteDto(1, 10.767676, 106.689362, "Station 1"));
        listRoute.add(new RouteDto(1, 10.76767, 106.690941, "Station 2"));
        listRoute.add(new RouteDto(2, 10.76767635, 106.68936157, ""));
        listRoute.add(new RouteDto(1, 10.768788, 106.693699, "Station 3"));
        listDto.add(new BusRouteInstructionDto(2, listRoute, "#41ba34", 18, 30, 10000));
        listDto.add(new WalkInstructionDto(1, 2, 3,
                new CoordDto(10.769504, 106.695587, "Station 4"),
                new CoordDto(10.770937, 106.697164, "My Home"),
                9, 150));
        adapter = new RouteInstructionAdapter(listDto, this.getContext());
        adapter.setmListener(this);
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(true);
    }

    @Override
    public void onItemClick(View view, Object data, int position) {
        Log.d("OnItemClick", "onItemCLick");
    }
}

