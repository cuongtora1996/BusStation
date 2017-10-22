package com.example.fpt.busstation.ui.behaviorbottom.viewholder;

import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.fpt.busstation.R;
import com.example.fpt.busstation.ui.base.BaseRcvAdapter;
import com.example.fpt.busstation.ui.base.BaseRcvViewHolder;
import com.example.fpt.busstation.ui.behaviorbottom.dto.StationDto;

/**
 * Created by Vi Nguyen on 17/10/2017.
 */

public class StationViewHolder extends BaseRcvViewHolder {
    CardView cardView;
    TextView tvStaName;
    TextView tvStaAddr;

    public StationViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.cvStation);
        tvStaName = (TextView) itemView.findViewById(R.id.tvStaName);
        tvStaAddr = (TextView) itemView.findViewById(R.id.tvStaAddr);
    }

    @Override
    public void bindItem(final BaseRcvAdapter.OnItemListener listener, final Object item, final int position) {
        StationDto station = (StationDto) item;
        tvStaName.setText(station.getStationName());
        tvStaAddr.setText(station.getStationAddress());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, item, position);
            }
        });

    }
}
