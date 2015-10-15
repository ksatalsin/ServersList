package com.xyrality.slist.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.xyrality.slist.R;
import com.xyrality.slist.model.Server;
import com.xyrality.slist.model.ServerLisRequest;
import com.xyrality.slist.model.ServerLisResponse;
import com.xyrality.slist.net.RetrofitErrorHandler;
import com.xyrality.slist.net.XyralityApi;
import com.xyrality.slist.ui.listeners.IFragmentAuthListener;

import java.util.List;
import java.util.UUID;

import retrofit.Callback;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class MainActivity extends AppCompatActivity implements IFragmentAuthListener {


    private final String TAG = this.getClass().getSimpleName();
    private UserLoginTask mAuthTask = null;
    private Fragment mContent;
    private XyralityApi mXyralityApi;
    private RetrofitErrorHandler mRetrofitError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeRepository();
        initializeLoginFragment();
    }

    private void initializeRepository() {

        mRetrofitError = new RetrofitErrorHandler();

        final RestAdapter.Builder restAdapterBuilder = new RestAdapter.Builder()
                //.setClient(new OkClient(okHttpClient))
                .setLogLevel(retrofit.RestAdapter.LogLevel.FULL)
                .setEndpoint(XyralityApi.apiURL)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade requestFacade) {
                        requestFacade.addHeader("Content-Type",
                                "application/json");
                        requestFacade.addHeader("Accept",
                                "application/json");
                    }
                })
                .setErrorHandler(mRetrofitError);
        RestAdapter restAdapter = restAdapterBuilder.build();
        mXyralityApi = restAdapter.create(XyralityApi.class);
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
    public void onLoginClick(String login, String password) {
        String deviceId = getDeviceId();
        String deviceType = String.format("%s %s",android.os.Build.MODEL, android.os.Build.VERSION.RELEASE);

        //Change my mind about using AsyncTask
       // new UserLoginTask(email,password,deviceType, deviceId).execute();




     /*

       //This is how I see Post
        ServerLisRequest request = new ServerLisRequest();
        request.setDeviceId(deviceId);
        request.setDeviceType(deviceType);
        request.setLogin(login);
        request.setPassword(password);

        mXyralityApi.getAuthForServerList(request, new Callback<ServerLisResponse>() {
            @Override
            public void success(ServerLisResponse serverLisResponse, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
*/


        showLoading(true);
        //This should be GET
        mXyralityApi.getAuthForServerList(login, password, deviceType, deviceId, new Callback<ServerLisResponse>() {
            @Override
            public void success(ServerLisResponse serverLisResponse, Response response) {
                showLoading(false);
                if (serverLisResponse != null && serverLisResponse.getAllAvailableWorlds() != null)
                    initializeServerListFragment(serverLisResponse);
            }

            @Override
            public void failure(RetrofitError error) {
                showLoading(false);
            }
        });
    }



    /**
     * can't rely on MAC address, if there are no rights in Manifest or device has no module
     * @return uniqe token
     */
    private String getDeviceId() {

        String token = "";
        try {
            final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

            final String tmDevice, tmSerial, androidId;
            tmDevice = "" + tm.getDeviceId();
            tmSerial = "" + tm.getSimSerialNumber();
            androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
            token = deviceUuid.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.e(TAG, "token: "  + token);
        return token;
    }


    /**
     * AsyncTask used to get server list for specified credential.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, ServerLisResponse> {

        private final String mEmail;
        private final String mPassword;
        private String mDeviceType;
        private String mDeviceId;

        UserLoginTask(String email, String password,String deviceType,String deviceId) {
            mEmail = email;
            mPassword = password;
            mDeviceType = deviceType;
            mDeviceId = deviceId;
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

        if(mContent instanceof LoginFragment){
            ((LoginFragment)mContent).showLoading(b);
        }
    }
}

