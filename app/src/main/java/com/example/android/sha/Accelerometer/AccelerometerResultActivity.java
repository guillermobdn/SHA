package com.example.android.sha.Accelerometer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.sha.Magnetic.MagneticResultActivity;
import com.example.android.sha.Magnetic.MagneticTestActivity;
import com.example.android.sha.MainActivity;
import com.example.android.sha.R;
import com.example.android.sha.SHAUtils;
import com.google.firebase.database.FirebaseDatabase;

public class AccelerometerResultActivity extends AppCompatActivity {

    Button butSi, butNo;
    boolean alltest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_result);

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

        FirebaseDatabase.getInstance().getReference().child(iid).child("accelerometer").setValue(ok);

        alltest =  getIntent().getBooleanExtra("alltest",false);

        if (alltest != true) {
            Intent main = new Intent(AccelerometerResultActivity.this, MainActivity.class);
            startActivity(main);
        }else{
            Intent main = new Intent(AccelerometerResultActivity.this, MagneticTestActivity.class);
            main.putExtra("alltest",alltest);
            startActivity(main);
        }
    }

    @Override
    public void onBackPressed() {

    }
}
