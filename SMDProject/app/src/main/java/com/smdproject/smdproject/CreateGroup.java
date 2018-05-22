package com.smdproject.smdproject;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appinvite.AppInviteInvitation;

public class CreateGroup extends AppCompatActivity {

    Uri postImage=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();

            postImage = uri;

            ImageView imageview = (ImageView) findViewById(R.id.grouppic);

            imageview.setImageURI(uri);

        }
    }

    public static final Uri getUriToDrawable(@NonNull Context context,
                                             @AnyRes int drawableId) {
        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + context.getResources().getResourcePackageName(drawableId)
                + '/' + context.getResources().getResourceTypeName(drawableId)
                + '/' + context.getResources().getResourceEntryName(drawableId) );
        return imageUri;
    }


    public void createGroup(View v){
        EditText gn = findViewById(R.id.groupname);
        if(gn.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this,"Group name can not be empty.",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent i = new Intent();
        i.putExtra("g_name",gn.getText().toString());

        if(postImage!=null) i.putExtra("groupPic",postImage.toString());
        else i.putExtra("groupPic",getUriToDrawable(this,R.drawable.defaultgroupicon));

        this.setResult(RESULT_OK,i);
        finish();
    }


    public void attachGroupPic(View v){

        Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/*");
        startActivityForResult(i,0);

    }

}
