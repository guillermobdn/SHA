package com.example.android.sha.Battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sha.R;

public class BatteryTestActivity extends AppCompatActivity {
    private TextView textBattery,textCharge;
    Button checkBattery, testEnd;
    ImageView battery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_test);

        checkBattery = (Button) this.findViewById(R.id.checkBattery);
        textBattery = (TextView) this.findViewById(R.id.textBattery);
        textCharge = (TextView) this.findViewById(R.id.textCharge);
        battery = (ImageView) this.findViewById(R.id.battery);
        testEnd = (Button) this.findViewById(R.id.testEnd);

        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        checkBattery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBatteryState(textCharge);
            }
        });

        testEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BatteryTestActivity.this, BatteryResultActivity.class));
            }
        });

    }

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            textBattery.setText(String.valueOf(level) + "%");
        }
    };

    public void checkBatteryState(View sender) {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = registerReceiver(null, filter);

        int chargeState = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        String strState;

        switch (chargeState) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
            case BatteryManager.BATTERY_STATUS_FULL:
                strState = "CARGANDO";
                battery.setImageResource(R.drawable.battery_charge);
                break;
            default:
                strState = "NO ESTÁ CARGANDO";
                battery.setImageResource(R.drawable.battery_alert );
        }

        textCharge.setText(strState);
    }
}
