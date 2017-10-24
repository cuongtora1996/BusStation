package com.example.fpt.busstation.ui.behaviorbottom.viewholder;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.example.fpt.busstation.R;
import com.example.fpt.busstation.ui.base.BaseRcvAdapter;
import com.example.fpt.busstation.ui.base.BaseRcvViewHolder;
import com.example.fpt.busstation.ui.behaviorbottom.dto.WalkInstructionDto;

/**
 * Created by Vi Nguyen on 22/10/2017.
 */

public class InsWalkEndViewHolder extends BaseRcvViewHolder {
    CardView cardView;
    TextView tvFromStation;
    TextView tvWalkTo;

    public InsWalkEndViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.cvInsWalkEnd);
        tvFromStation = (TextView) itemView.findViewById(R.id.tvFromStation);
        tvWalkTo = (TextView) itemView.findViewById(R.id.tvWalkTo);

    }

    @Override
    public void bindItem(final BaseRcvAdapter.OnItemListener listener, final Object item, final int position) {
        WalkInstructionDto walkIns = (WalkInstructionDto) item;
        if (walkIns.getEndType() == 3) {//toi diem can den
            tvFromStation.setText("Xuống xe tại trạm " + walkIns.getBeginCoord().getName());
        }
        tvWalkTo.setText("Đi bộ đến " + walkIns.getEndCoord().getName());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, item, position);
            }
        });
    }
}
