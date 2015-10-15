package com.xyrality.slist.ui.adapters;

/**
 * Created by youyou on 15.10.2015.
 */


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyrality.slist.R;
import com.xyrality.slist.model.Server;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ServerRecyclerAdapter extends RecyclerView.Adapter<ServerRecyclerAdapter.ViewHolder> {
    private ArrayList<Server> mServers;

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_icon)
        ImageView mStatusIcon;

        @Bind(R.id.tv_desc)
        TextView mDescText;

         @Bind(R.id.tv_name)
        TextView mNameText;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public void add(int position, Server item) {
        mServers.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(Server item) {
        int position = mServers.indexOf(item);
        mServers.remove(position);
        notifyItemRemoved(position);
    }

    public ServerRecyclerAdapter(@NonNull ArrayList<Server> servers) {
        mServers = servers;
    }

    @Override
    public ServerRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_server, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Server item = mServers.get(position);
        holder.mNameText.setText(item.getName());
        //TODO: localize item
        holder.mDescText.setText("Country: " + item.getCountry() + "Lang: " + item.getLanguage());

        if(item.getWorldStatus().getDescription().equalsIgnoreCase("online")){
            holder.mStatusIcon.setImageResource(R.drawable.ic_online);
        }else{
            holder.mStatusIcon.setImageResource(R.drawable.ic_ofline);
        }

        holder.mDescText.setText("Country: " + item.getCountry());


    }

    @Override
    public int getItemCount() {
        return mServers.size();
    }

}