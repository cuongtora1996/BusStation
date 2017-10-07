package com.example.fpt.busstation.ui.behaviorbottom.TestFragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.fpt.busstation.R;
import com.example.fpt.busstation.ui.base.BaseFragment;
import com.example.fpt.busstation.ui.base.BaseRcvAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFirstFragment extends BaseFragment implements BaseRcvAdapter.OnItemListener {

    RecyclerView recyclerView;
    TestAdapter testAdapter;
    public TestFirstFragment() {
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
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testModels.add(new TestModel("Quan12","gan day lam"));
        testAdapter = new TestAdapter(testModels,this.getContext());
        testAdapter.setmListener(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(testAdapter);
        recyclerView.setNestedScrollingEnabled(true);

    }

    @Override
    public void onItemClick(View view, Object data, int position) {
        Log.d("OnItemClick",((TestModel)data).getStationName());
    }
}
