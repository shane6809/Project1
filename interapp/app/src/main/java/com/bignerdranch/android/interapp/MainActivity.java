package com.bignerdranch.android.interapp;

import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.File;
import android.content.Intent;
import android.graphics.Bitmap;


public class MainActivity extends Activity {

    Button mImageButton;
    ImageView provileView;
    static final int CAMERA_REQUESt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageButton = (Button)findViewById(R.id.photobutton);
        provileView = (ImageView)findViewById(R.id.image_view);

        mImageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(takePictureIntent, CAMERA_REQUESt);
                }

            }
        });
    }

    private File getFile(){

        File folder = new File(Environment.getExternalStorageDirectory(),"camera_app");

        if(!folder.exists())
        {
            folder.mkdir();
        }

        File image_file = new File(folder,"cam_image.jpg");
        return image_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUESt && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            provileView.setImageBitmap(imageBitmap);
        }
    }

}
