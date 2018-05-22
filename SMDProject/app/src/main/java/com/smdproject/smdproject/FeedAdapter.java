package com.smdproject.smdproject;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.firebase.ui.storage.images.FirebaseImageLoader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import database.Post;

/**
 * Created by Abdullah on 2/28/2018.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {
    private List<Post> items;
    private int itemLayout;
    private MainActivity context;

    public FeedAdapter(List<Post> items, int itemLayout,Context c) {
        this.items = items;
        this.itemLayout = itemLayout;
        this.context=(MainActivity)c;
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v= LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        return new FeedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder,int position){
        if(items!=null && holder!=null){
            if(items.get(position).getPostman().dp!=null)
                holder.dp.setImageURI(Uri.parse(items.get(position).getPostman().dp));

            holder.name.setText(items.get(position).getPostman().getName());
//            if(items.get(position).getGroup().getNicknames().containsKey(items.get(position).getPostman().getUid()))
//                holder.nickname.setText("@"+items.get(position).getGroup().getNicknames().get(items.get(position).getPostman().getUid()));

            String timestamp=items.get(position).getStamp();
            Date d =new Date();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String today=df.format(d);
            String[] todays=today.split(" ");
            String[] timestamps=timestamp.split(" ");
            if(todays[0].equalsIgnoreCase(timestamps[0])){
                timestamp=timestamps[1];
                timestamp=timestamp.substring(0,timestamp.length()-3);
            }
            else{
                timestamp=timestamps[1];
                timestamp=timestamp.substring(0,timestamp.length()-3);
                timestamp=timestamps[0]+" "+timestamp;
            }

            holder.timestamp.setText(timestamp);
            holder.text.setText(items.get(position).getText());

            if(items.get(position).getText().equalsIgnoreCase("")){
                holder.text.setVisibility(TextView.GONE);
            }
            else holder.text.setVisibility(TextView.VISIBLE);

            if(items.get(position).getImage()==null){
                holder.image.setVisibility(ImageView.GONE);
            }
            else {
                holder.image.setVisibility(ImageView.VISIBLE);
                if (items.get(position).getImage() != null) {
                    StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("Images/"+items.get(position).getUid()+","+items.get(position).getPid());
                    Glide.with(context).load(mStorage).into(holder.image);
                }
            }
        }
    }

    @Override
    public int getItemCount(){
        if(items!=null)
            return items.size();
        else return 0;
    }
}