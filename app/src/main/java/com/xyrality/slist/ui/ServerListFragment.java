package com.xyrality.slist.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.xyrality.slist.model.ServerLisResponse;


public class ServerListFragment extends Fragment {

    private static final String ARG_KEY_SERVER_LIST = "server_list";

    public static ServerListFragment newInstance(ServerLisResponse slr) {
        ServerListFragment f = new ServerListFragment();
        Bundle args = new Bundle();

        //should be parcelable :)
        args.putSerializable(ARG_KEY_SERVER_LIST, slr);
        f.setArguments(args);
        return f;
    }
}
