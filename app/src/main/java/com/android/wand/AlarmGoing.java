package com.android.wand;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AlarmGoing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_going);
        Button dismiss = findViewById(R.id.dismiss);
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MainActivity.stopVibrate();
                MainActivity.alarmTriggered = false;
                MainActivity.renderUI(getApplicationContext());
                finish();
            }
        });
    }
}
