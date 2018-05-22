package com.smdproject.smdproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.Group;
import database.Message;
import database.Post;
import database.User;

/**
 * Created by Abdullah on 2/28/2018.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {
    private List<Message> items;
    private int itemLayoutIn;
    private int itemLayoutOut;
    private MainActivity context;
    private TTSManager ttsManager;

    public ChatAdapter(List<Message> items, int itemLayoutIn, int itemLayoutOut,TTSManager ttsManager, MainActivity context) {
        this.items = items;
        this.itemLayoutIn = itemLayoutIn;
        this.itemLayoutOut = itemLayoutOut;
        this.context=context;
        this.ttsManager=ttsManager;

    }

    @Override
    public int getItemViewType(int position){
        if(items.get(position).getSenderid().equals(context.getCurrentUser().getUid()))return 2;
        else return 1;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v=null;

        if(viewType==1)v=LayoutInflater.from(parent.getContext()).inflate(itemLayoutIn,parent,false);
        else v=LayoutInflater.from(parent.getContext()).inflate(itemLayoutOut,parent,false);

        return new ChatViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ChatViewHolder holder,final int position){
        if(items!=null && holder!=null){

            User sender=null;
            for(User b:context.getCurrentGroup().getMembers()){
                if(items.get(position).getSenderid().equals(b.getUid())){
                    sender=b;
                    break;
                }

            }
            if(sender==null)return;

            Group group=context.getCurrentGroup();


            if(sender.dp!=null)
                holder.dp.setImageURI(Uri.parse(sender.dp));

            if(group.getNicknames().containsKey(sender.getUid()))
                holder.nickname.setText(group.getNicknames().get(sender.getUid()));
            else holder.nickname.setText(sender.getName());

            String timestamp=items.get(position).getStamp().toString();
            String today=(new Date()).toString();

            String[] todays=today.split(" ");
            String[] timestamps=timestamp.split(" ");

            if(todays[0].equalsIgnoreCase(timestamps[0]) && todays[1].equalsIgnoreCase(timestamps[1])
                    && todays[2].equalsIgnoreCase(timestamps[2])){
                timestamp=timestamps[3];
                timestamp=timestamp.substring(0,timestamp.length()-3);
            }
            else{
                timestamp=timestamps[3];
                timestamp=timestamp.substring(0,timestamp.length()-3);
                timestamp=timestamps[0]+timestamps[1]+timestamps[2]+timestamp;
            }

            holder.timestamp.setText(timestamp);
            holder.text.setText(items.get(position).getText());

            holder.b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){

                    String text = items.get(position).getText();
                    ttsManager.initQueue(text);
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