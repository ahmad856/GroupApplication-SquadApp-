package com.smdproject.smdproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import database.Event;
import database.User;

public class HorizontalEventAdapter extends RecyclerView.Adapter<HorizontalViewHolder> {
    private List<Event> items;
    private int itemLayout;
    private MainActivity context;


    public HorizontalEventAdapter(List <Event> items, int itemLayout, MainActivity context) {
        this.items = items;
        this.itemLayout = itemLayout;
        this.context=context;
    }

    @Override
    public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v= LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        return new HorizontalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HorizontalViewHolder holder, final int position){
        if(items!=null && holder!=null){
            holder.name.setText(((Event) items.get(position)).getName());


            holder.v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){

                    String loc=context.getCurrentGroup().getEvents().get(position).getLocation();
                    String[] locs=loc.split(",");
                    CameraPosition position=new CameraPosition.Builder()
                            .target(new LatLng(Double.parseDouble(locs[0]),Double.parseDouble(locs[1])))
                            .zoom(17).build();
                    context.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));

                }
            });

        }
    }

    @Override
    public int getItemCount(){
        if(items!=null)
            return items.size();
        else return 0;
    }
}