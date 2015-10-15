package com.xyrality.slist.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.xyrality.slist.R;
import com.xyrality.slist.model.ServerLisResponse;
import com.xyrality.slist.ui.listeners.IFragmentAuthListener;

public class MainActivity extends AppCompatActivity implements IFragmentAuthListener {


    private UserLoginTask mAuthTask = null;
    private Fragment mContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeLoginFragment();
    }

    private void initializeLoginFragment() {
        mContent = LoginFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, mContent).commit();
    }

    private void initializeServerListFragment(ServerLisResponse slr) {
        mContent = ServerListFragment.newInstance(slr);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, mContent).commit();
    }

    @Override
    public void onLoginClick(String email, String password) {
        new UserLoginTask(email,password).execute();
    }


    /**
     * AsyncTask used to get server list for specified credential.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, ServerLisResponse> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected ServerLisResponse doInBackground(Void... params) {


            return new ServerLisResponse();
        }

        @Override
        protected void onPostExecute(final ServerLisResponse slr) {
            mAuthTask = null;
            showLoading(false);

            //init slr

        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showLoading(false);
        }
    }

    private void showLoading(boolean b) {
        //mContent.showLoading
    }
}

