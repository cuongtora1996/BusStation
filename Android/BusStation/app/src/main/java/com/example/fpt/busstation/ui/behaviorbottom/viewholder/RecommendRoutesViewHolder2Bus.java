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
    TextView tvRouteName;
    TextView tvNoOfBus1;
    TextView tvNoOfBus2;

    public RecommendRoutesViewHolder2Bus(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.cvRoute2Bus);
        tvDuration = (TextView) itemView.findViewById(R.id.tvDuration);
        tvRouteName = (TextView) itemView.findViewById(R.id.tvRouteName);
        tvNoOfBus1 = (TextView) itemView.findViewById(R.id.tvNoOfBus1);
        tvNoOfBus2 = (TextView) itemView.findViewById(R.id.tvNoOfBus2);
    }

    @Override
    public void bindItem(final BaseRcvAdapter.OnItemListener listener, final Object item, final int position) {
        RecommendRoutesDto dto = (RecommendRoutesDto) item;
        tvRouteName.setText(((RecommendRoutesDto) item).generateRouteName());
        String timeUnit = "phút";
        Double duration = dto.getDuration();

        if (duration >= 100) {
            duration = duration / 100;
            timeUnit = "giờ";
        }

        tvDuration.setText(String.valueOf((double) Math.round(duration)) + "\n" + timeUnit);
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
