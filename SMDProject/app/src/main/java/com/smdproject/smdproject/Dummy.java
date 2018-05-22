package com.smdproject.smdproject;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;

import database.Group;
import database.User;

public class Dummy extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private User user;
    private Group group;
    String number;
    TextView t;
    TextView g;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);
        mAuth = FirebaseAuth.getInstance();
        btn = findViewById(R.id.button5);
        btn.setEnabled(false);
        t=findViewById(R.id.textView);
        g=findViewById(R.id.textView13);

        if(getIntent()!=null && getIntent().getExtras()!=null)
            number=getIntent().getExtras().getString("mobileno");

//        TelephonyManager tMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        number = tMgr.getLine1Number();


        if(mAuth.getCurrentUser().getPhotoUrl() != null && mAuth.getCurrentUser().getDisplayName() != null && number!=null){
            user=new User(mAuth.getCurrentUser().getUid(),mAuth.getCurrentUser().getPhotoUrl().toString(),mAuth.getCurrentUser().getDisplayName(),number);
            t.setText("You Signed in with Name : "+mAuth.getCurrentUser().getDisplayName()+", Mobile No. : "+number);
        }
        else{
            Intent i = new Intent(this, GetName.class);
            startActivityForResult(i, 103);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null){
            Intent auth=new Intent(this,LoginActivity.class);
            startActivity(auth);
            finish();
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==103 && resultCode==RESULT_OK && data!=null && data.getExtras()!=null){
            String s= data.getExtras().getString("p_name");
            String pic= data.getExtras().getString("groupPic");

            user = new User(mAuth.getCurrentUser().getUid(),pic,s,number);
            t.setText("You Signed in with Name : "+s+", Mobile Number : "+number);
        }
        else if(requestCode==1111 && resultCode==RESULT_OK && data!=null && data.getExtras()!=null){
            String pic= data.getExtras().getString("dp");

            group=new Group(data.getExtras().getString("g_name"),user.getUid());
            group.setGroupPic(pic);
            g.setText("You created Group : "+group.getName());

        }
    }

    public void nCreateGroup(View v){
        Intent i = new Intent(this,CreateGroup.class);
        startActivityForResult(i,1111);
        btn.setEnabled(true);
    }

    public void onClickCont(View v){
        Intent ii=new Intent(this,MainActivity.class);
        ii.putExtra("user", user);
        ii.putExtra("group",group);
        startActivity(ii);
        finish();
    }

    public void onClickJoin(View v){
        Intent i = new Intent(this,LoginGroup.class);
        i.putExtra("user",user);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed(){

    }

}
