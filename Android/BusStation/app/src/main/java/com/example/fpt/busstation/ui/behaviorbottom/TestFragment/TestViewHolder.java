package com.example.fpt.busstation.ui.behaviorbottom.TestFragment;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.example.fpt.busstation.R;
import com.example.fpt.busstation.ui.base.BaseRcvAdapter;
import com.example.fpt.busstation.ui.base.BaseRcvViewHolder;

/**
 * Created by cuong on 9/29/2017.
 */

public class TestViewHolder extends BaseRcvViewHolder {
    TextView tvStationName;
    TextView tvStationAddress;
    CardView cardView;
    public TestViewHolder(View itemView){
        super(itemView);
        tvStationName =(TextView) itemView.findViewById(R.id.tvStationName);
        tvStationAddress =(TextView) itemView.findViewById(R.id.tvStationAddress);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
    }
    @Override
    public void bindItem(final BaseRcvAdapter.OnItemListener listener, final Object item, final int position) {
        TestModel testModel = (TestModel) item;
        tvStationName.setText(testModel.getStationName());
        tvStationAddress.setText(testModel.getStationAddress());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,item,position);
            }
        });
    }
}
