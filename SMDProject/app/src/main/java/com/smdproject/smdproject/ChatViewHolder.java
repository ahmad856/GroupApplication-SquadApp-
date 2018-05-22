package com.smdproject.smdproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Abdullah on 4/18/2018.
 */

public class ChatViewHolder extends RecyclerView.ViewHolder{

    public ImageView dp;
    public TextView nickname;
    public TextView timestamp;
    public TextView text;
    public Button b;

    public ChatViewHolder(View view){
        super(view);
        dp = (ImageView) view.findViewById(R.id.dpOnMessage);
        nickname = (TextView) view.findViewById(R.id.nicknameOnMessage);
        timestamp = (TextView)view.findViewById(R.id.timestampOnMessage);
        text = (TextView) view.findViewById(R.id.textOnMessage);
        b=(Button)view.findViewById(R.id.tts);

    }

}
