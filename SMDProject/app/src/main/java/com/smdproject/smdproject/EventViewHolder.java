package com.smdproject.smdproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EventViewHolder extends RecyclerView.ViewHolder {

    public TextView eventName;
    public TextView time;
    public TextView place;
    public TextView description;
    public Button b;

    public EventViewHolder(View view){
        super(view);
        eventName = view.findViewById(R.id.nameEvent);
        description = view.findViewById(R.id.desc);
        time = view.findViewById(R.id.eventTime);
        place = view.findViewById(R.id.eventPlace);
        b=view.findViewById(R.id.dButton);
    }


}
