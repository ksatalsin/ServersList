package com.xyrality.slist.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.xyrality.slist.R;
import com.xyrality.slist.ui.listeners.IFragmentAuthListener;

import butterknife.Bind;
import butterknife.ButterKnife;


public class LoginFragment extends Fragment {

    private IFragmentAuthListener mActivity;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Bind(R.id.btn_login)
    Button mBtnLogin;

    @Bind(R.id.ll_login)
    LinearLayout mLoginView;

    @Bind(R.id.et_login)
    EditText mLoginInput;

    @Bind(R.id.et_password)
    EditText mPasswordInput;


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

        initializeLoginView();
    }

    private void initializeLoginView() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }



            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(mLoginInput.getError()))
                    mLoginInput.setError(null);

                if (!TextUtils.isEmpty(mPasswordInput.getError()))
                    mPasswordInput.setError(null);
            }
        };

        mLoginInput.addTextChangedListener(textWatcher);
        mPasswordInput.addTextChangedListener(textWatcher);

        mLoginInput.setText("android.test@xyrality.com");
        mPasswordInput.setText("password");
    }

    private void attemptLogin() {

        String login = mLoginInput.getText().toString();
        String password = mPasswordInput.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(password)) {
            mPasswordInput.setError(getString(R.string.error_field_required));
            focusView = mPasswordInput;
            cancel = true;
        }

        if (TextUtils.isEmpty(login)) {
            mLoginInput.setError(getString(R.string.error_field_required));
            focusView = mLoginInput;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
           mActivity.onLoginClick(login, password);
        }
    }

}
