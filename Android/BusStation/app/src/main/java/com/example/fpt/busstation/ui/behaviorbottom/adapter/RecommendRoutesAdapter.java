package com.example.fpt.busstation.ui.behaviorbottom.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fpt.busstation.R;
import com.example.fpt.busstation.ui.base.BaseRcvAdapter;
import com.example.fpt.busstation.ui.behaviorbottom.dto.RecommendRoutesDto;
import com.example.fpt.busstation.ui.behaviorbottom.viewholder.RecommendRoutesViewHolder2Bus;
import com.example.fpt.busstation.ui.behaviorbottom.viewholder.RecommendRoutesViewHolder3Bus;

import java.util.List;

/**
 * Created by Vi Nguyen on 21/10/2017.
 */

public class RecommendRoutesAdapter extends BaseRcvAdapter {
    List<RecommendRoutesDto> listRecommend;
    Context context;

    public RecommendRoutesAdapter(List<RecommendRoutesDto> listRecommend, Context context) {
        this.listRecommend = listRecommend;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        Log.d("onCreateViewHolder", "viewTye: " + viewType);
        switch (viewType) {
            case 0:
                break;
            case 1: //1 bus
                break;
            case 2: //2 bus
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_route_style2_2bus, parent, false);
                viewHolder = new RecommendRoutesViewHolder2Bus(view);
                break;

            case 3: //3 bus
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_route_style2_3bus, parent, false);
                viewHolder = new RecommendRoutesViewHolder3Bus(view);
                break;
            default:
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        Log.d("onBindViewHolder", "viewType: "+ viewType);
        switch (viewType) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                Log.d("Bind View Holder", "Position of item: " + position);
                RecommendRoutesViewHolder2Bus viewHolder2Bus = (RecommendRoutesViewHolder2Bus) holder;
                viewHolder2Bus.bindItem(mListener, listRecommend.get(position), position);
                break;
            case 3:
                Log.d("Bind View Holder", "Position of item: " + position);
                RecommendRoutesViewHolder3Bus viewHolder3Bus = (RecommendRoutesViewHolder3Bus) holder;
                viewHolder3Bus.bindItem(mListener, listRecommend.get(position), position);
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (listRecommend.get(position).getTotalBus() == 2) {
            Log.d("getItemViewType", "Position of item: " + position);
            return 2;
        } else if (listRecommend.get(position).getTotalBus() == 3) {
            Log.d("getItemViewType", "Position of item: " + position);
            return 3;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        if (listRecommend != null && listRecommend.size() > 0) {
            return listRecommend.size();
        }
        return 1;
    }

}