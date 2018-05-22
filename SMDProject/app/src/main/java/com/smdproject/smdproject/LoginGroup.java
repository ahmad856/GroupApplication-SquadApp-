package com.smdproject.smdproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import database.Event;
import database.Group;
import database.Message;
import database.Post;
import database.User;

public class LoginGroup extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ArrayList<String> groups;
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groups=new ArrayList<>();
        setContentView(R.layout.activity_login_group);
        mDatabase=LoginActivity.getDatabase().getReference("currentGroup");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    Group g=ds.getValue(Group.class);
                    groups.add(g.getGroupId());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        u = (User)getIntent().getSerializableExtra("user");
    }

    public String getLoc(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                return location.getLatitude()+","+location.getLongitude();
            }
        }
        return null;
    }

    public void onClickGroupId(View v){
        EditText ed=findViewById(R.id.editText);
        if(ed.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this,"Please enter a Group ID.",Toast.LENGTH_SHORT).show();
            return;
        }

        boolean exist=false;
        for(int i=0;i<groups.size();i++){
            if(groups.get(i).equals(ed.getText().toString())){
                exist=true;
            }
        }
        if(!exist){
            Toast.makeText(this, "Wrong GroupId. Please enter again", Toast.LENGTH_SHORT).show();
            return;
        }
        Group g = new Group();
        g.setMembers(new ArrayList<User>());
        g.setNicknames(new HashMap<String, String>());
        g.setPosts(new ArrayList<Post>());
        g.setMessages(new ArrayList<Message>());
        g.setEvents(new ArrayList<Event>());

        g.setGroupId(ed.getText().toString());
        g.getMembers().add(u);
        u.setLocation(getLoc());
        Intent i = new Intent(this,MainActivity.class);
        i.putExtra("user", u);
        i.putExtra("group",g);
        startActivity(i);
        finish();
    }
}
