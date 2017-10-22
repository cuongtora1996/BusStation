package com.example.fpt.busstation.ui.behaviorbottom.viewholder;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.example.fpt.busstation.R;
import com.example.fpt.busstation.ui.base.BaseRcvAdapter;
import com.example.fpt.busstation.ui.base.BaseRcvViewHolder;
import com.example.fpt.busstation.ui.behaviorbottom.dto.BusRouteInstructionDto;
import com.example.fpt.busstation.ui.behaviorbottom.dto.RouteDto;

import java.util.List;

/**
 * Created by Vi Nguyen on 22/10/2017.
 */

public class InsBusRouteViewHolder extends BaseRcvViewHolder {
    CardView cardView;
    TextView tvTitle;
    TextView tvFromStation;
    TextView tvToStation;

    public InsBusRouteViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.cvInsBusRoute);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        tvFromStation = (TextView) itemView.findViewById(R.id.tvFromStation);
        tvToStation = (TextView) itemView.findViewById(R.id.tvToStation);
    }

    @Override
    public void bindItem(final BaseRcvAdapter.OnItemListener listener, final Object item, final int position) {
        BusRouteInstructionDto dto = (BusRouteInstructionDto) item;
        tvTitle.setText("Di tuyen xe so " + dto.getBusNum());
        List<RouteDto> routeDtos = dto.getRouteDto();
        if (routeDtos.get(0).getType() == 1) {
            tvFromStation.setText(routeDtos.get(0).getName() + "=>");
        }
        if (routeDtos.get(routeDtos.size() - 1).getType() == 1) {
            tvToStation.setText(routeDtos.get(routeDtos.size() - 1).getName());
        }
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, item, position);
            }
        });

    }
}
