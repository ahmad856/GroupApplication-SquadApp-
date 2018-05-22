package com.smdproject.smdproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HorizontalViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public ImageView im;
    public Button v;

    public HorizontalViewHolder(View view){
        super(view);
        name = view.findViewById(R.id.nameH);
        im=view.findViewById(R.id.testv);
        v=view.findViewById(R.id.v);
    }
}
