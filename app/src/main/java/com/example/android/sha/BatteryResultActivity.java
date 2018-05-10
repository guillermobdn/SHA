package com.example.android.sha;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.FirebaseDatabase;

public class BatteryResultActivity extends AppCompatActivity {

    Button butSi, butNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_result);

        butSi = findViewById(R.id.butSi);
        butNo = findViewById(R.id.butNo);

        butSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               saveResults(true);
            }
        });

        butNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveResults(false);
            }
        });
    }

    void saveResults(boolean ok){
        String iid = SHAUtils.getAndroidId(this);

        FirebaseDatabase.getInstance().getReference().child(iid).child("battery").setValue(ok);

        Intent main = new Intent(BatteryResultActivity.this, MainActivity.class);
        startActivity(main);
    }
}
