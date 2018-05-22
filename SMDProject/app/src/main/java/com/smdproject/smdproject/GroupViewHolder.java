package com.smdproject.smdproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GroupViewHolder extends RecyclerView.ViewHolder{
    public ImageView groupic;
    public TextView groupname;
    public TextView menu;

    public GroupViewHolder(View view){
        super(view);
        groupic = view.findViewById(R.id.imageView3);
        groupname = view.findViewById(R.id.textView3);
        menu = view.findViewById(R.id.textView4);
    }
}