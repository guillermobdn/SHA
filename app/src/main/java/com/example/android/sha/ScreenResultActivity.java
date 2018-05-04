package com.example.android.sha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScreenResultActivity extends AppCompatActivity {

    Button butSi, butNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_result);

        butSi = (Button) findViewById(R.id.butSi);
        butNo = (Button) findViewById(R.id.butNo);

        butSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(ScreenResultActivity.this, MainActivity.class);
                startActivity(main);
            }
        });

        butNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(ScreenResultActivity.this, MainActivity.class);
                startActivity(main);
            }
        });
    }
}
