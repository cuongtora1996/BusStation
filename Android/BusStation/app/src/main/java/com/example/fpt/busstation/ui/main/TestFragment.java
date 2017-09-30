package com.example.fpt.busstation.ui.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fpt.busstation.R;
import com.example.fpt.busstation.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends BaseFragment {

    RecyclerView recyclerView;
    TestAdapter testAdapter;
    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getContentViewResource() {
        return R.layout.fragment_test;
    }

    @Override
    protected void onInit(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        List<TestModel> testModels = new ArrayList<>();
        testModels.add(new TestModel("Cho cau","gan day lam"));
        testModels.add(new TestModel("Toky","gan day lam"));
        testAdapter = new TestAdapter(testModels,this.getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(testAdapter);
        recyclerView.setNestedScrollingEnabled(true);
        hideKeyboard();
    }

}
