package com.example.fpt.busstation.ui.behaviorbottom.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.fpt.busstation.R;
import com.example.fpt.busstation.ui.base.BaseFragment;
import com.example.fpt.busstation.ui.base.BaseRcvAdapter;
import com.example.fpt.busstation.ui.behaviorbottom.adapter.RecommendRoutesAdapter;
import com.example.fpt.busstation.ui.behaviorbottom.dto.BusDto;
import com.example.fpt.busstation.ui.behaviorbottom.dto.RecommendRoutesDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vi Nguyen on 21/10/2017.
 */

public class RecommendRoutesFragment extends BaseFragment implements BaseRcvAdapter.OnItemListener {
    RecyclerView recyclerView;
    RecommendRoutesAdapter adapter;

    @Override
    protected int getContentViewResource() {
        return R.layout.rcv_route_recommend_fragment;
    }

    @Override
    protected void onInit(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rcv_route_recommend);
        List<RecommendRoutesDto> list = new ArrayList<>();
        list.add(new RecommendRoutesDto("Tuyen duong 18-24", 112, 90, 2, "18, 24"));
        list.add(new RecommendRoutesDto("Tuyen duong 55-18", 112, 90, 2, "55, 18"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-24", 112, 90, 2, "18, 24"));
        list.add(new RecommendRoutesDto("Tuyen duong 01-19-20", 112, 90, 3, "01, 19, 20"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-04-25", 112, 90, 3, "18, 04, 25"));
        list.add(new RecommendRoutesDto("Tuyen duong 139-32", 112, 90, 2, "139, 32"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-24", 112, 90, 2, "18, 24"));
        list.add(new RecommendRoutesDto("Tuyen duong 55-18", 112, 90, 2, "55, 18"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-24", 112, 90, 2, "18, 24"));
        list.add(new RecommendRoutesDto("Tuyen duong 01-19-20", 112, 90, 3, "01, 19, 20"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-04-25", 112, 90, 3, "18, 04, 25"));
        list.add(new RecommendRoutesDto("Tuyen duong 139-32", 112, 90, 2, "139, 32"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-24", 112, 90, 2, "18, 24"));
        list.add(new RecommendRoutesDto("Tuyen duong 55-18", 112, 90, 2, "55, 18"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-24", 112, 90, 2, "18, 24"));
        list.add(new RecommendRoutesDto("Tuyen duong 01-19-20", 112, 90, 3, "01, 19, 20"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-04-25", 112, 90, 3, "18, 04, 25"));
        list.add(new RecommendRoutesDto("Tuyen duong 139-32", 112, 90, 2, "139, 32"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-24", 112, 90, 2, "18, 24"));
        list.add(new RecommendRoutesDto("Tuyen duong 55-18", 112, 90, 2, "55, 18"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-24", 112, 90, 2, "18, 24"));
        list.add(new RecommendRoutesDto("Tuyen duong 01-19-20", 112, 90, 3, "01, 19, 20"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-04-25", 112, 90, 3, "18, 04, 25"));
        list.add(new RecommendRoutesDto("Tuyen duong 139-32", 112, 90, 2, "139, 32"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-24", 112, 90, 2, "18, 24"));
        list.add(new RecommendRoutesDto("Tuyen duong 55-18", 112, 90, 2, "55, 18"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-24", 112, 90, 2, "18, 24"));
        list.add(new RecommendRoutesDto("Tuyen duong 01-19-20", 112, 90, 3, "01, 19, 20"));
        list.add(new RecommendRoutesDto("Tuyen duong 18-04-25", 112, 90, 3, "18, 04, 25"));
        list.add(new RecommendRoutesDto("Tuyen duong 139-32", 112, 90, 2, "139, 32"));
        adapter = new RecommendRoutesAdapter(list, this.getContext());
        adapter.setmListener(this);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);

    }

    @Override
    public void onItemClick(View view, Object data, int position) {
        Log.d("OnItemClick", ((RecommendRoutesDto) data).getRecommendRouteId());
    }
}
