package com.example.fpt.busstation.ui.behaviorbottom.viewholder;

import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.fpt.busstation.R;
import com.example.fpt.busstation.ui.base.BaseRcvAdapter;
import com.example.fpt.busstation.ui.base.BaseRcvViewHolder;
import com.example.fpt.busstation.ui.behaviorbottom.dto.RecommendRoutesDto;

/**
 * Created by Vi Nguyen on 21/10/2017.
 */

public class RecommendRoutesViewHolder2Bus extends BaseRcvViewHolder {
    CardView cardView;
    TextView tvDuration;
    TextView tvRouteId;
    TextView tvNoOfBus1;
    TextView tvNoOfBus2;

    public RecommendRoutesViewHolder2Bus(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.cvRoute2Bus);
        tvDuration = (TextView) itemView.findViewById(R.id.tvDuration);
        tvRouteId = (TextView) itemView.findViewById(R.id.tvRouteId);
        tvNoOfBus1 = (TextView) itemView.findViewById(R.id.tvNoOfBus1);
        tvNoOfBus2 = (TextView) itemView.findViewById(R.id.tvNoOfBus2);
    }

    @Override
    public void bindItem(final BaseRcvAdapter.OnItemListener listener, final Object item, final int position) {
        RecommendRoutesDto dto = (RecommendRoutesDto) item;
        tvRouteId.setText(((RecommendRoutesDto) item).getRecommendRouteId());
        tvDuration.setText(String.valueOf(((RecommendRoutesDto) item).getDuration()) + "\n phut");
        String[] busNumber = ((RecommendRoutesDto) item).getListBusNo().split(", ");
        if (busNumber.length == 2) {
            tvNoOfBus1.setText(busNumber[0]);
            tvNoOfBus2.setText(busNumber[1]);
        } else {
            Log.d("Bind Item View Holder", "List: " + busNumber.toString());
        }

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, item, position);
            }
        });

    }
}
