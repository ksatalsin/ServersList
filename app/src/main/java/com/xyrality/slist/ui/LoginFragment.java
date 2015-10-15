package com.xyrality.slist.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xyrality.slist.R;
import com.xyrality.slist.ui.listeners.IFragmentAuthListener;

import butterknife.ButterKnife;


public class LoginFragment extends Fragment {


    private IFragmentAuthListener mActivity;


    public static LoginFragment newInstance() {
        LoginFragment f = new LoginFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity) {
            activity = (Activity) context;

            try {
                mActivity = (IFragmentAuthListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " must be a IFragmentAuthListener");
            }
        }
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void attemptLogin() {

        //mActivity.onLoginClick("","");
    }


}
