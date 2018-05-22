package com.smdproject.smdproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import database.Group;
import database.User;

public class MainGroupActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener{
    private GestureDetector gestureDetector;
    private Context c;
    private RecyclerView rv;
    ArrayList<Group> data=new ArrayList<>();
    private GroupRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager lm;
    private User u;
    String groupid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_group);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv = findViewById(R.id.recyclerView);
        c=this;
        lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.addOnItemTouchListener(this);
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new GroupRecyclerViewAdapter(data, R.layout.group_layout,c);
        rv.setAdapter(adapter);

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                View child = rv.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if(child != null){
                    //if tap was performed on some recyclerview row item
                    int i = rv.getChildAdapterPosition(child);	//index of item which was clicked
                    Group g = data.get(i);
                    Intent ii=new Intent();
                    ii.putExtra("Group",(Serializable)g);
                    MainGroupActivity.this.setResult(RESULT_OK,ii);
                    finish();
                }
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent dataIntent) {
        super.onActivityResult(requestCode, resultCode, dataIntent);
        if(requestCode == 1122 && resultCode==RESULT_OK && dataIntent != null && dataIntent.getExtras() != null){
            Group g = new Group(dataIntent.getExtras().getString("g_name"),u.getUid());
            data.add(g);
            adapter.notifyDataSetChanged();
        }
        if(requestCode == 1123 && resultCode==RESULT_OK && dataIntent != null && dataIntent.getExtras() != null){//group join
            groupid=dataIntent.getExtras().getString("g_id");
        }
    }

    public void onCreateGroup(View v){
        Intent i = new Intent(this,CreateGroup.class);
        startActivityForResult(i,1122);
    }

    public void onChangeGroup(View v){

    }
}