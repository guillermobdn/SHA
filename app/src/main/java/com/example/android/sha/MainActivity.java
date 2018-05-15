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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button camButton, screenButton, batteryButton, vibrateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //----------------------------------------

        camButton = findViewById(R.id.camera);
        camButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CameraInfoActivity.class));
            }
        });

        //----------------------------------------

        screenButton = findViewById(R.id.screen);
        screenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScreenInfoActivity.class));
            }
        });

        //----------------------------------------
        batteryButton = findViewById(R.id.battery);
        batteryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BatteryInfoActivity.class));
            }
        });

        //----------------------------------------
        vibrateButton = findViewById(R.id.vibration);
        vibrateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, VibratorInfoActivity.class));
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

                setButtonColor(batteryButton, shaResult.battery);
                setButtonColor(camButton, shaResult.camera);
                setButtonColor(screenButton, shaResult.screen);
                setButtonColor(vibrateButton, shaResult.vibrate);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    void setButtonColor(Button button, Boolean ok){
        if(ok == null){
            return;
        }

        if(ok){
            button.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
        }else{
            button.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }
    }

}
