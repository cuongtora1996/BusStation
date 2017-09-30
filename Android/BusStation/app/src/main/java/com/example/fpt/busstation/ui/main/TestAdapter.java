package com.example.fpt.busstation.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.fpt.busstation.R;
import com.example.fpt.busstation.ui.base.BaseMvpView;
import com.example.fpt.busstation.ui.base.BaseRcvAdapter;
import com.example.fpt.busstation.ui.base.BaseRcvViewHolder;

import java.util.List;

/**
 * Created by cuong on 9/29/2017.
 */

public class TestAdapter extends BaseRcvAdapter {
    private List<TestModel> testModels;
    private Context mContext;
    public TestAdapter(List<TestModel> testModels, Context mContext){

        this.mContext = mContext;
        this.testModels = testModels;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TestViewHolder((LayoutInflater.from(parent.getContext())).inflate(R.layout.cardview_item_test,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final TestViewHolder testViewHolder = (TestViewHolder) holder;
        testViewHolder.bindItem(mListener,testModels.get(position),position);
    }


    @Override
    public int getItemCount() {
        if (testModels != null && testModels.size() > 0) {
            return testModels.size();
        } else {
            return 1;
        }
    }
    public void addItems(List<TestModel> testModels) {
        this.testModels.addAll(testModels);
        notifyDataSetChanged();
    }
}
