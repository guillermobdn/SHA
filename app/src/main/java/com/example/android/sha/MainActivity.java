package com.example.android.sha;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.android.sha.Accelerometer.AccelerometerInfoActivity;
import com.example.android.sha.Audio.AudioInfoActivity;
import com.example.android.sha.Audio.AudioTestActivity;
import com.example.android.sha.Battery.BatteryInfoActivity;
import com.example.android.sha.Camera.CameraInfoActivity;
import com.example.android.sha.FlashLight.FlashlightInfoActivity;
import com.example.android.sha.FlashLight.FlashlightTestActivity;
import com.example.android.sha.Gyroscope.GyroscopeInfoActivity;
import com.example.android.sha.Magnetic.MagneticInfoActivity;
import com.example.android.sha.Microphone.MicrophoneInfoActivity;
import com.example.android.sha.Microphone.MicrophoneTestActivity;
import com.example.android.sha.Proximity.ProximityInfoActivity;
import com.example.android.sha.Screen.ScreenInfoActivity;
import com.example.android.sha.SensorLight.SensorLightInfoActivity;
import com.example.android.sha.Vibrate.VibratorInfoActivity;
import com.example.android.sha.Vibrate.VibratorTestActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button camButton, screenButton, batteryButton, vibrateButton, sensorlightButton, flashlightButton, gyroscopeButton, audioButton, microButton
            , proximityButton, magneticButton, accelerometerButton,alltestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //----------------------------------------

        camButton = (Button) findViewById(R.id.camera);
        camButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CameraInfoActivity.class));
            }
        });

        //----------------------------------------

        screenButton = (Button) findViewById(R.id.screen);
        screenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScreenInfoActivity.class));
            }
        });

        //----------------------------------------
        batteryButton = (Button) findViewById(R.id.battery);
        batteryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BatteryInfoActivity.class));
            }
        });

        //----------------------------------------
        vibrateButton = (Button) findViewById(R.id.vibration);
        vibrateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, VibratorTestActivity.class));
            }
        });

        //----------------------------------------
        sensorlightButton = (Button) findViewById(R.id.lightsensor);
        sensorlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SensorLightInfoActivity.class));
            }
        });

        //----------------------------------------
        flashlightButton = (Button) findViewById(R.id.flashlight);
        flashlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FlashlightTestActivity.class));
            }
        });

        //----------------------------------------
        gyroscopeButton = (Button) findViewById(R.id.gyroscope);
        gyroscopeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GyroscopeInfoActivity.class));
            }
        });

        //----------------------------------------
        audioButton = (Button) findViewById(R.id.audio);
        audioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AudioTestActivity.class));
            }
        });

        //----------------------------------------
        microButton = (Button) findViewById(R.id.mic);
        microButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MicrophoneTestActivity.class));
            }
        });

        //----------------------------------------
        proximityButton = (Button) findViewById(R.id.proximity);
        proximityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProximityInfoActivity.class));
            }
        });

        //----------------------------------------
        magneticButton = (Button) findViewById(R.id.magnetic);
        magneticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MagneticInfoActivity.class));
            }
        });

        //----------------------------------------
        accelerometerButton = (Button) findViewById(R.id.accelerometer);
        accelerometerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AccelerometerInfoActivity.class));
            }
        });

        //----------------------------------------
        alltestButton = (Button) findViewById(R.id.run_all);
        alltestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AllTestActivity.class));
            }
        });


        loadResults();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    void loadResults(){
        String iid = SHAUtils.getAndroidId(this);

        FirebaseDatabase.getInstance().getReference().child(iid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SHAResult shaResult = dataSnapshot.getValue(SHAResult.class);

                if(shaResult == null){
                    setButtonColor(batteryButton, null);
                    setButtonColor(camButton, null);
                    setButtonColor(screenButton, null);
                    setButtonColor(vibrateButton, null);
                    setButtonColor(sensorlightButton, null);
                    setButtonColor(flashlightButton, null);
                    setButtonColor(gyroscopeButton, null);
                    setButtonColor(audioButton, null);
                    setButtonColor(microButton, null);
                    setButtonColor(proximityButton, null);
                    setButtonColor(magneticButton, null);
                    setButtonColor(accelerometerButton, null);
                }else {
                    setButtonColor(batteryButton, shaResult.battery);
                    setButtonColor(camButton, shaResult.camera);
                    setButtonColor(screenButton, shaResult.screen);
                    setButtonColor(vibrateButton, shaResult.vibrate);
                    setButtonColor(sensorlightButton, shaResult.sensorlight);
                    setButtonColor(flashlightButton, shaResult.flashlight);
                    setButtonColor(gyroscopeButton, shaResult.gyroscope);
                    setButtonColor(audioButton, shaResult.audio);
                    setButtonColor(microButton, shaResult.microphone);
                    setButtonColor(proximityButton, shaResult.proximity);
                    setButtonColor(magneticButton, shaResult.magnetic);
                    setButtonColor(accelerometerButton, shaResult.accelerometer);
                    alltestButton.setBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    void setButtonColor(Button button, Boolean ok){
        if(ok == null){
            return;
        }else if(ok){
            button.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
        }else{
            button.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
    }

}
