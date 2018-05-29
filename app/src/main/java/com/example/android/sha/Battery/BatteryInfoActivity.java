package com.example.android.sha.Battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.sha.R;

public class BatteryInfoActivity extends AppCompatActivity {

    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_info);

        Button test = (Button) findViewById(R.id.batteryTest);

        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        tvInfo = (TextView) findViewById(R.id.info);


        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BatteryInfoActivity.this, BatteryTestActivity.class));
            }
        });
    }

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {

            String tec = intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);

            float cap = getBatteryCapacity();

            int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);

            int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH,0);

            tvInfo.setText(Html.fromHtml("<p><b>Tecnología: </b>"+ tec +"</p><p><b>Capacidad: </b>" + cap +
                    " mAh </p><p><b>Voltage: </b>"+ voltage + " ¡'mV </p><p><b>Salud actual: </b>" + health + "</p>"));

        }
    };

    private float getBatteryCapacity(){
        BatteryManager bm = (BatteryManager) getSystemService(Context.BATTERY_SERVICE);
        Long charg = bm.getLongProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
        Long capacity = bm.getLongProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        if(charg != null && capacity != null){
            float value = (((float) charg/ (float) capacity)*100f);
            return value;
        }
        return 0;
    }
}
