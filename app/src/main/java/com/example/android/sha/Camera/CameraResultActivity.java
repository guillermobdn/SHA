package com.example.android.sha.Camera;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.sha.Battery.BatteryTestActivity;
import com.example.android.sha.MainActivity;
import com.example.android.sha.R;
import com.example.android.sha.SHAUtils;
import com.google.firebase.database.FirebaseDatabase;

public class CameraResultActivity extends AppCompatActivity {

    Button butSi, butNo;
    Boolean alltest = getIntent().getBooleanExtra("alltest",true);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_result);

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

        FirebaseDatabase.getInstance().getReference().child(iid).child("camera").setValue(ok);


        if (!alltest) {
            Intent main = new Intent(CameraResultActivity.this, MainActivity.class);
            startActivity(main);
        }else {
            Intent main = new Intent(CameraResultActivity.this, BatteryTestActivity.class);
            startActivity(main);
        }
    }
}
