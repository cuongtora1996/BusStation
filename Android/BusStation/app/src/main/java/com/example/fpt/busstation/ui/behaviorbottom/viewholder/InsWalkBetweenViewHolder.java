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

public class InsWalkBetweenViewHolder extends BaseRcvViewHolder {
    CardView cardView;
    TextView tvTitle;


    public InsWalkBetweenViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.cvInsWalkBetween);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);

    }

    @Override
    public void bindItem(final BaseRcvAdapter.OnItemListener listener, final Object item, final int position) {
        WalkInstructionDto walkIns = (WalkInstructionDto) item;

        tvTitle.setText("Chuyển tuyến tại: "+walkIns.getBeginCoord().getName());



        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, item, position);
            }
        });
    }
}
