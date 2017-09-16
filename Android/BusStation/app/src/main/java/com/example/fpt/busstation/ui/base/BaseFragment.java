package com.example.fpt.busstation.ui.base;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.fpt.busstation.util.CommonUtils;

/**
 * Created by cuong on 9/11/2017.
 */

public abstract class BaseFragment extends Fragment implements BaseMvpView {
    private BaseActivity mActivity;
    private ProgressDialog mProgressDialog;
    //Gán layout
    protected abstract int getContentViewResource();
    //Gán presenter, các thứ trong đây
    protected abstract void onInit(View view);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getContentViewResource(), container, false);
        onInit(view);
        // Inflate the layout for this fragment
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            mActivity.onFragmentAttached();
        }
    }

    @Override
    public void onDetach() {
        mActivity=null;
        super.onDetach();
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }
    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this.getContext());
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onError(String message) {
        if (mActivity != null) {
            mActivity.onError(message);
        }
    }

    @Override
    public void showMessage(String message) {
        if (mActivity != null) {
            mActivity.showMessage(message);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        if (mActivity != null) {
            return mActivity.isNetworkConnected();
        }
        return false;
    }

    @Override
    public boolean isServiceRunning(Class<?> serviceClass) {
        if (mActivity != null) {
            return mActivity.isServiceRunning(serviceClass);
        }
        return false;
    }

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
