package com.example.android.sha.FlashLight;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.sha.R;

public class FlashlightTestActivity extends AppCompatActivity {

    private ImageView imageFlashlight;
    private static final int CAMERA_REQUEST = 50;
    private boolean flashLightStatus = false;
    Button testEnd;
    boolean alltest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight_test);
        imageFlashlight = (ImageView) findViewById(R.id.imageFlashlight);
        testEnd = (Button) findViewById(R.id.testEnd);

        final boolean hasCameraFlash = getPackageManager().
                hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);


        ActivityCompat.requestPermissions(FlashlightTestActivity.this, new String[] {android.Manifest.permission.CAMERA}, CAMERA_REQUEST);

        imageFlashlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasCameraFlash) {
                    if (flashLightStatus)
                        flashLightOff();
                    else
                        flashLightOn();
                } else {
                    Toast.makeText(FlashlightTestActivity.this, "No flash available on your device",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        final Intent resultactivity = new Intent(FlashlightTestActivity.this, FlashLightResultActivity.class);

        alltest = getIntent().getBooleanExtra("alltest",false);

        testEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultactivity.putExtra("alltest",alltest);
                startActivity(resultactivity);

            }
        });
    }

    private void flashLightOn() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, true);
            }
            flashLightStatus = true;
            imageFlashlight.setImageResource(R.drawable.flash);
        } catch (CameraAccessException e) {

        }
    }

    private void flashLightOff() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, false);
            }
            flashLightStatus = false;
            imageFlashlight.setImageResource(R.drawable.flash_off);
        } catch (CameraAccessException e) {
            //res de res
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        flashLightOff();
    }

}
