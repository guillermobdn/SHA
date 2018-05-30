package com.example.android.sha.Camera;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sha.R;

public class CameraTestActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    private static final int CAMERA_REQUEST = 50;
    Button testEnd;
    boolean alltest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_test);

        textView = (TextView)findViewById(R.id.text);
        testEnd = (Button)findViewById(R.id.testEnd);
        imageView = (ImageView)findViewById(R.id.imageView);

        ActivityCompat.requestPermissions(CameraTestActivity.this, new String[] {Manifest.permission.CAMERA}, CAMERA_REQUEST);


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);

        final Intent resultactivity = new Intent(CameraTestActivity.this, CameraResultActivity.class);

        alltest = getIntent().getBooleanExtra("alltest",false);

        testEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultactivity.putExtra("alltest",alltest);
                startActivity(resultactivity);

            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
    }
}
