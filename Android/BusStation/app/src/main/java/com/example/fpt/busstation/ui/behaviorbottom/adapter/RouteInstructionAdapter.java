package com.example.fpt.busstation.ui.behaviorbottom.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fpt.busstation.R;
import com.example.fpt.busstation.ui.base.BaseRcvAdapter;
import com.example.fpt.busstation.ui.behaviorbottom.dto.BusRouteInstructionDto;
import com.example.fpt.busstation.ui.behaviorbottom.dto.WalkInstructionDto;
import com.example.fpt.busstation.ui.behaviorbottom.viewholder.InsBusRouteViewHolder;
import com.example.fpt.busstation.ui.behaviorbottom.viewholder.InsWalkEndViewHolder;
import com.example.fpt.busstation.ui.behaviorbottom.viewholder.InsWalkBeginViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vi Nguyen on 22/10/2017.
 */

public class RouteInstructionAdapter extends BaseRcvAdapter {
    private List<Object> lstDto = new ArrayList<>();
    private Context context;
    private static final int TYPE_WALK_BEGIN = 0;
    private static final int TYPE_WALK_END = 1;
    private static final int TYPE_BUS = 2;

    public RouteInstructionAdapter(List<Object> lstDto, Context context) {
        this.lstDto = lstDto;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_ins_walk_begin, parent, false);
                viewHolder = new InsWalkBeginViewHolder(view);
                break;

            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_ins_walk_end, parent, false);
                viewHolder = new InsWalkEndViewHolder(view);
                break;

            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_ins_bus_route, parent, false);
                viewHolder = new InsBusRouteViewHolder(view);
                break;
            default:
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:
                InsWalkBeginViewHolder insWalkBeginViewHolder = (InsWalkBeginViewHolder) holder;
                insWalkBeginViewHolder.bindItem(mListener, lstDto.get(position), position);
                break;
            case 1:
                InsWalkEndViewHolder insWalkEndViewHolder = (InsWalkEndViewHolder) holder;
                insWalkEndViewHolder.bindItem(mListener, lstDto.get(position), position);
                break;

            case 2:
                InsBusRouteViewHolder insBusRouteViewHolder = (InsBusRouteViewHolder) holder;
                insBusRouteViewHolder.bindItem(mListener, lstDto.get(position), position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (lstDto != null && lstDto.size() > 0) {
            return lstDto.size();
        }
        return 1;
    }


    @Override
    public int getItemViewType(int position) {
        if (lstDto.get(position) instanceof BusRouteInstructionDto) {
            return TYPE_BUS;
        } else if (lstDto.get(position) instanceof WalkInstructionDto) {
            WalkInstructionDto dto = (WalkInstructionDto) lstDto.get(position);
            if (dto.getBeginType() == 1 && dto.getEndType() == 2) {
                return TYPE_WALK_BEGIN;
            } else if (dto.getBeginType() == 2 && dto.getEndType() == 3) {
                return TYPE_WALK_END;
            }
        }
        return -1;
    }

    public void changeItems(List<Object> dtos){
        lstDto = dtos;
        notifyDataSetChanged();
    }
}
