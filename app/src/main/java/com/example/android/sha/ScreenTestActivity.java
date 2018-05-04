package com.example.android.sha;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScreenTestActivity extends AppCompatActivity {

    ImageView phone;
    TextView text1;
    Button continuar;
    LinearLayout fondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_test);
        phone = (ImageView) findViewById(R.id.phone);
        text1 = (TextView) findViewById(R.id.text1);
        continuar = (Button) findViewById(R.id.continuar);
        fondo = (LinearLayout) findViewById(R.id.fondo);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone.setVisibility(View.INVISIBLE);
                text1.setVisibility(View.INVISIBLE  );
                continuar.setVisibility(View.INVISIBLE  );

                fondo.setBackgroundColor(Color.BLACK);

                fondo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fondo.setBackgroundColor(Color.WHITE);

                        fondo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                phone.setVisibility(View.VISIBLE);
                                text1.setText("Pulse el botón para finalizar el test.");
                                text1.setVisibility(View.VISIBLE);

                                continuar.setText("Finalizar test");
                                continuar.setVisibility(View.VISIBLE);

                                continuar.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(ScreenTestActivity.this, ScreenResultActivity.class));
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }
}
