package com.example.fpt.busstation.ui.behaviorbottom.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.fpt.busstation.R;
import com.example.fpt.busstation.ui.base.BaseFragment;
import com.example.fpt.busstation.ui.base.BaseRcvAdapter;
import com.example.fpt.busstation.ui.behaviorbottom.adapter.BusAdapter;
import com.example.fpt.busstation.ui.behaviorbottom.dto.BusDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vi Nguyen on 17/10/2017.
 */

public class BusFragment extends BaseFragment implements BaseRcvAdapter.OnItemListener {
    RecyclerView recyclerView;
    BusAdapter busAdapter;

    public BusFragment() {
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.rcv_bus_fragment;
    }

    @Override
    protected void onInit(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rcv_bus);
        List<BusDto> busDtoList = new ArrayList<BusDto>();
        busDtoList.add(new BusDto("Tuyen so 01", "Ben Thanh - BX Cho Lon"));
        busDtoList.add(new BusDto("Tuyen so 02", "Ben Thanh - BX Mien Tay"));
        busDtoList.add(new BusDto("Tuyen so 03", "Ben Thanh - BX Thanh Loc"));
        busDtoList.add(new BusDto("Tuyen so 01", "Ben Thanh - BX Cho Lon"));
        busDtoList.add(new BusDto("Tuyen so 02", "Ben Thanh - BX Mien Tay"));
        busDtoList.add(new BusDto("Tuyen so 03", "Ben Thanh - BX Thanh Loc"));
        busDtoList.add(new BusDto("Tuyen so 01", "Ben Thanh - BX Cho Lon"));
        busDtoList.add(new BusDto("Tuyen so 02", "Ben Thanh - BX Mien Tay"));
        busDtoList.add(new BusDto("Tuyen so 03", "Ben Thanh - BX Thanh Loc"));
        busDtoList.add(new BusDto("Tuyen so 01", "Ben Thanh - BX Cho Lon"));
        busDtoList.add(new BusDto("Tuyen so 02", "Ben Thanh - BX Mien Tay"));
        busDtoList.add(new BusDto("Tuyen so 03", "Ben Thanh - BX Thanh Loc"));
        busDtoList.add(new BusDto("Tuyen so 01", "Ben Thanh - BX Cho Lon"));
        busDtoList.add(new BusDto("Tuyen so 02", "Ben Thanh - BX Mien Tay"));
        busDtoList.add(new BusDto("Tuyen so 03", "Ben Thanh - BX Thanh Loc"));
        busDtoList.add(new BusDto("Tuyen so 01", "Ben Thanh - BX Cho Lon"));
        busDtoList.add(new BusDto("Tuyen so 02", "Ben Thanh - BX Mien Tay"));
        busDtoList.add(new BusDto("Tuyen so 03", "Ben Thanh - BX Thanh Loc"));
        busDtoList.add(new BusDto("Tuyen so 01", "Ben Thanh - BX Cho Lon"));
        busDtoList.add(new BusDto("Tuyen so 02", "Ben Thanh - BX Mien Tay"));
        busDtoList.add(new BusDto("Tuyen so 03", "Ben Thanh - BX Thanh Loc"));
        busAdapter = new BusAdapter(busDtoList, this.getContext());
        busAdapter.setmListener(this);
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(busAdapter);
        recyclerView.setNestedScrollingEnabled(true);

    }

    @Override
    public void onItemClick(View view, Object data, int position) {
        Log.d("OnItemClick", ((BusDto) data).getBusNumber());
    }
}
