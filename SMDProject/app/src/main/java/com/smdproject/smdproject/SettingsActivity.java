package com.smdproject.smdproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appinvite.AppInviteInvitation;

import java.util.ArrayList;

import database.Event;
import database.Group;
import database.User;

public class SettingsActivity extends AppCompatActivity {


    Group currentGroup;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SettingsUsersAdapter adapter=null;
        if(currentGroup!=null)
            adapter=new SettingsUsersAdapter(currentGroup.getMembers(),R.layout.settings_row_layout,this);
        else
            adapter=new SettingsUsersAdapter(null,R.layout.settings_row_layout,this);
        RecyclerView rc = findViewById(R.id.settingsMembers);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);

        rc.setLayoutManager(layoutManager);
        rc.setItemAnimator(new DefaultItemAnimator());
        rc.setAdapter(adapter);


    }



    public void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode,resultCode,data);


        if(requestCode==0 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            Uri uri=data.getData();

            ImageView imageview=(ImageView)findViewById(R.id.settingsGroupPic);

            currentGroup.setGroupPic(uri.toString());

            imageview.setImageURI(uri);

            Toast.makeText(this,"Group Pic changed.",Toast.LENGTH_SHORT).show();

        }
        else if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            Uri uri=data.getData();

            ImageView imageview=(ImageView)findViewById(R.id.settingsDP);

            imageview.setImageURI(uri);

            Toast.makeText(this,"Profile Pic changed.",Toast.LENGTH_SHORT).show();
        }



    }

    public void setGroupName(View v){

        String name=((EditText)findViewById(R.id.settingsGroupName)).getText().toString();
        if(name.equals(""))Toast.makeText(this,"Group Name can not be empty.",Toast.LENGTH_SHORT).show();
        else{

            currentGroup.setName(name);
            Toast.makeText(this,"Group Name changed.",Toast.LENGTH_SHORT).show();

        }


    }

    public void attachGroupPic(View v){

        Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/*");
        startActivityForResult(i,0);

    }
    public void attachDP(View v){

        Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/*");
        startActivityForResult(i,1);

    }


}
