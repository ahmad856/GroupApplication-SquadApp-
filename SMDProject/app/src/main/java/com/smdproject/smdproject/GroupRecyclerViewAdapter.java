package com.smdproject.smdproject;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import database.Group;

import java.util.ArrayList;

public class GroupRecyclerViewAdapter extends RecyclerView.Adapter<GroupViewHolder>{
    private ArrayList<Group> items;
    private int itemLayout;
    private Context mCtx;

    public GroupRecyclerViewAdapter(ArrayList<Group> r,int itemLayout,Context mctx){
        this.items=r;
        this.itemLayout=itemLayout;
        this.mCtx=mctx;
    }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new GroupViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final GroupViewHolder holder, int position) {
        if(items != null && holder != null){
           // holder.groupic.setImageResource(items.get(position).getGroupPic());
            holder.groupname.setText(items.get(position).getName());
            holder.menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //creating a popup menu
                    PopupMenu popup = new PopupMenu(mCtx, holder.menu);
                    //inflating menu from xml resource
                    popup.inflate(R.menu.group_menu);
                    //adding click listener
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menu1:
                                    //handle menu1 click
                                    break;
                                case R.id.menu2:
                                    //handle menu2 click
                                    break;
                            }
                            return false;
                        }
                    });
                    //displaying the popup
                    popup.show();
                }
            });
        }
    }

    @Override
    public int getItemCount(){
        if(items != null)
            return items.size();
        else
            return 0;
    }
}