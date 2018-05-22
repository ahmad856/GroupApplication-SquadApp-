package com.smdproject.smdproject;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import database.Event;
import database.User;

public class HorizontalUsersAdapter extends RecyclerView.Adapter<HorizontalViewHolder> {
    private List<User> items;
    private int itemLayout;
    private MainActivity context;


    public HorizontalUsersAdapter(List <User> items, int itemLayout, MainActivity context) {
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
            holder.name.setText(((User) items.get(position)).getName());
            if(((User) items.get(position)).dp!=null) {
                Glide.with(context).load(Uri.parse(items.get(position).dp)).into(holder.im);
            }

            holder.im.setColorFilter(Color.TRANSPARENT);

            holder.v.setOnClickListener(new View.OnClickListener() {
               @Override
                public void onClick(View v){

                   String loc=context.getCurrentGroup().getMembers().get(position).getLocation();
                   if(loc==null){
                       Toast.makeText(context,"Location of this member not available at the moment.",Toast.LENGTH_SHORT).show();
                       return;
                   }
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