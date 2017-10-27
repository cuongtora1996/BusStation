package com.example.fpt.busstation.ui.behaviorbottom.viewholder;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.example.fpt.busstation.R;
import com.example.fpt.busstation.ui.base.BaseRcvAdapter;
import com.example.fpt.busstation.ui.base.BaseRcvViewHolder;
import com.example.fpt.busstation.ui.behaviorbottom.dto.BusRouteInstructionDto;
import com.example.fpt.busstation.ui.behaviorbottom.dto.RouteDto;
import com.example.fpt.busstation.util.LayoutUtils;

import java.util.List;

/**
 * Created by Vi Nguyen on 22/10/2017.
 */

public class InsBusRouteViewHolder extends BaseRcvViewHolder {
    CardView cardView;
    TextView tvTitle;
    TextView tvRouteInfo;
    private static final int TXT_LENGTH_LIMIT = 35;

    public InsBusRouteViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.cvInsBusRoute);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        tvRouteInfo = (TextView) itemView.findViewById(R.id.tvRouteInfo);
    }

    @Override
    public void bindItem(final BaseRcvAdapter.OnItemListener listener, final Object item, final int position) {
        BusRouteInstructionDto dto = (BusRouteInstructionDto) item;
        tvTitle.setText("Đi tuyến xe số " + dto.getBusNum());
        List<RouteDto> routeDtos = dto.getRouteDto();
        String fromStation = "", toStation = "";
        if (routeDtos.get(0).getType() == 1) {
            fromStation = routeDtos.get(0).getName() + " - ";
        }
        if (routeDtos.get(routeDtos.size() - 1).getType() == 1) {
            toStation = routeDtos.get(routeDtos.size() - 1).getName();
        }
        String mess = fromStation + toStation;
        LayoutUtils.setLinesForTextView(mess, TXT_LENGTH_LIMIT,tvRouteInfo);
        tvRouteInfo.setText(mess);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, item, position);
            }
        });

    }
}
