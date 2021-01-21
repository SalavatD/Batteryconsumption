package com.anytrash.batteryconsumption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    BatteryManager mBatteryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        mBatteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
    }

    public void onClick(View view) {
        textView.setText(String.valueOf(mBatteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE) / 1000) + getString(R.string.mah2));
        button.setEnabled(false);

        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                button.setText(String.valueOf(millisUntilFinished / 1000) + getString(R.string.sec));
            }

            public void onFinish() {
                button.setEnabled(true);
                button.setText(R.string.discover);
            }
        }.start();
    }
}
