package com.android.wand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddAlarmActivity extends AppCompatActivity {
    int hour, minute;
    Spinner hours;
    Spinner minutes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        Intent intent = this.getIntent();

        Button submit = findViewById(R.id.submit);

        hours = findViewById(R.id.hour);
        minutes = findViewById(R.id.minute);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour = Integer.parseInt(hours.getSelectedItem().toString());
                minute = Integer.parseInt(minutes.getSelectedItem().toString());
                MainActivity.alarms.add(new Alarm(hour, minute));
                MainActivity.renderUI(getApplicationContext());
                finish();
            }
        });

    }
}
