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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class GetName extends AppCompatActivity {

    Uri postImage=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_name);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();

            postImage = uri;

            ImageView imageview = (ImageView) findViewById(R.id.imageView4);

            imageview.setImageURI(uri);

        }
    }

    public void attachDP(View v){

        Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/*");
        startActivityForResult(i,0);

    }

    public static final Uri getUriToDrawable(@NonNull Context context,
                                             @AnyRes int drawableId) {
        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + context.getResources().getResourcePackageName(drawableId)
                + '/' + context.getResources().getResourceTypeName(drawableId)
                + '/' + context.getResources().getResourceEntryName(drawableId) );
        return imageUri;
    }

    public void onClickUp(View v){
        EditText e=findViewById(R.id.p_name);
        if(!e.getText().toString().equals("")) {
            String s = e.getText().toString();
            Intent i = new Intent();
            i.putExtra("p_name",s);

            if(postImage!=null) i.putExtra("dp",postImage.toString());
            else i.putExtra("dp",getUriToDrawable(this,R.drawable.defaultdp));

            this.setResult(RESULT_OK,i);
            finish();
        }
        else{
            Toast.makeText(this, "Name Field Cannot be empty!", Toast.LENGTH_SHORT).show();
        }
    }
}
