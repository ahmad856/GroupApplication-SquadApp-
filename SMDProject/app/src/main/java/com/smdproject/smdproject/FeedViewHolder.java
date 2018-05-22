package com.smdproject.smdproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;


/**
 * Created by Abdullah on 2/28/2018.
 */

public class FeedViewHolder extends RecyclerView.ViewHolder {

    public ImageView dp;
    public TextView name;
    public TextView nickname;
    public TextView timestamp;
    public TextView text;
    public ImageView image;

    public FeedViewHolder(View view){
        super(view);
        dp = (ImageView) view.findViewById(R.id.dpOnPost);
        name = (TextView) view.findViewById(R.id.nameOnPost);
        nickname = (TextView) view.findViewById(R.id.nicknameOnPost);
        timestamp = (TextView)view.findViewById(R.id.timestampOnPost);
        text = (TextView) view.findViewById(R.id.textOnPost);
        image= (ImageView)view.findViewById(R.id.imageOnPost);
    }
}
