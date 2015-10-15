package com.xyrality.slist.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xyrality.slist.R;
import com.xyrality.slist.model.ServerLisResponse;
import com.xyrality.slist.ui.adapters.ServerRecyclerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ServerListFragment extends Fragment {

    private static final String ARG_KEY_SERVER_LIST = "server_list";
    private ServerLisResponse mServerLisResponse;

    public static ServerListFragment newInstance(ServerLisResponse slr) {
        ServerListFragment f = new ServerListFragment();
        Bundle args = new Bundle();
        //should be parcelable :)
        args.putSerializable(ARG_KEY_SERVER_LIST, slr);
        f.setArguments(args);
        return f;
    }

    @Bind(R.id.rv_servers)
    RecyclerView mServersRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_list_servers, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mServersRecycler.setLayoutManager(linearLayoutManager);
        mServersRecycler.setHasFixedSize(true);
        mServerLisResponse = (ServerLisResponse) getArguments().getSerializable(ARG_KEY_SERVER_LIST);
        ServerRecyclerAdapter serverRecyclerAdapter = new ServerRecyclerAdapter(mServerLisResponse.getAllAvailableWorlds());
        mServersRecycler.setAdapter(serverRecyclerAdapter);
    }
}
