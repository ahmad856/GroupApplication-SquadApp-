package com.smdproject.smdproject;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import database.User;

public class SettingsUsersAdapter extends RecyclerView.Adapter<SettingsViewHolder> {
    private List<User> items;
    private int itemLayout;

    private SettingsActivity context;


    public SettingsUsersAdapter(List <User> items, int itemLayout, SettingsActivity context) {
        this.items = items;
        this.itemLayout = itemLayout;
        this.context=context;
    }

    @Override
    public SettingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v= LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        return new SettingsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final SettingsViewHolder holder, final int position){


        if(items!=null && holder!=null){

            holder.name.setText(((User) items.get(position)).getName());
            holder.nickname.setText(context.currentGroup.getNicknames().get(items.get(position).getUid()));

            if(((User) items.get(position)).dp!=null) {

                holder.dp.setImageURI(Uri.parse(items.get(position).dp));
            }

            holder.setNickname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){

                    context.currentGroup.getNicknames().put(items.get(position).getUid(),holder.nickname.getText().toString());
                }
            });

            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){

                    if(context.currentGroup.getAdminId().equals(context.currentUser.getUid())){

                        for(User u:context.currentGroup.getMembers()){
                            if(u.getUid().equals(items.get(position).getUid())) {
                                context.currentGroup.getMembers().remove(u);
                                break;
                            }
                        }

                        Toast.makeText(context,"Member deleted.",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(context,"Only group admin can delete a member.",Toast.LENGTH_SHORT).show();
                    }
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