package com.xyrality.slist.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;

import com.xyrality.slist.R;
import com.xyrality.slist.model.ServerLisResponse;
import com.xyrality.slist.net.RetrofitErrorHandler;
import com.xyrality.slist.net.XyralityApi;
import com.xyrality.slist.ui.listeners.IFragmentAuthListener;

import java.util.UUID;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements IFragmentAuthListener {

    private final String TAG = this.getClass().getSimpleName();
    private Fragment mContent;
    private XyralityApi mXyralityApi;
    private RetrofitErrorHandler mRetrofitError;
    private static final String ARG_KEY_SERVER_LIST = "server_list";
    private ServerLisResponse mServerLisResponse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeRepository();
        if (savedInstanceState != null)
            mServerLisResponse = (ServerLisResponse)savedInstanceState.getSerializable(ARG_KEY_SERVER_LIST);

        if(mServerLisResponse!=null){
            initializeServerListFragment(mServerLisResponse);
        }else{
            initializeLoginFragment();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mServerLisResponse!=null)
            outState.putSerializable(ARG_KEY_SERVER_LIST, mServerLisResponse);
    }

    private void initializeRepository() {
        mRetrofitError = new RetrofitErrorHandler();
        final RestAdapter.Builder restAdapterBuilder = new RestAdapter.Builder()
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
        mServerLisResponse = slr;
        mContent = ServerListFragment.newInstance(slr);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, mContent).commit();
    }

    @Override
    public void onLoginClick(String login, String password) {
        String deviceId = getDeviceId();
        String deviceType = String.format("%s %s",android.os.Build.MODEL, android.os.Build.VERSION.RELEASE);
        showLoading(true);
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
     * Can't rely on MAC address, if there are no rights in Manifest or device has no module
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
        return token;
    }

    private void showLoading(boolean b) {
        if(mContent instanceof LoginFragment){
            ((LoginFragment)mContent).showLoading(b);
        }
    }
}

