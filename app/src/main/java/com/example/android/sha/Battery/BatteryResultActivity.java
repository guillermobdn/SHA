package com.example.android.sha.Battery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.sha.FlashLight.FlashlightTestActivity;
import com.example.android.sha.MainActivity;
import com.example.android.sha.R;
import com.example.android.sha.SHAUtils;
import com.google.firebase.database.FirebaseDatabase;

public class BatteryResultActivity extends AppCompatActivity {

    Button butSi, butNo;
    Boolean alltest = getIntent().getBooleanExtra("alltest",true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_result);

        butSi = (Button) findViewById(R.id.butSi);
        butNo = (Button) findViewById(R.id.butNo);

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

        if (!alltest){
            Intent main = new Intent(BatteryResultActivity.this, MainActivity.class);
            startActivity(main);
        }else {
            Intent main = new Intent(BatteryResultActivity.this, FlashlightTestActivity.class);
            startActivity(main);
        }
    }
}
