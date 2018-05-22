package com.smdproject.smdproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingsViewHolder extends RecyclerView.ViewHolder {

    public ImageView dp;
    public TextView name;
    public EditText nickname;
    public Button delete;
    public Button setNickname;

    public SettingsViewHolder(View view){
        super(view);
        dp=view.findViewById(R.id.imageS);
        name = view.findViewById(R.id.nameS);
        nickname=view.findViewById(R.id.nicknameS);
        delete=view.findViewById(R.id.deleteS);
        setNickname=view.findViewById(R.id.setNickname);

    }


}
