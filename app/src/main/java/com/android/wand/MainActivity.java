package com.android.wand;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SymbolTable;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private AsyncTimeTicker time;
    static TableLayout table;
    static VibrateMotor vibration;
    static boolean alarmTriggered = false;

    static ArrayList<Alarm> alarms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarms = new ArrayList<Alarm>();
        //vibration = new VibrateMotor(this);

        time = new AsyncTimeTicker();
        time.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        table = (TableLayout) findViewById(R.id.table);
        FloatingActionButton addAlarm = findViewById(R.id.floatingActionButton4);
        addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddAlarmActivity.class);
                startActivity(intent);
            }
        });
        Alarm alarm = new Alarm(20, 24);
        alarms.add(alarm);
        renderUI(this);
    }

    static String intToStringTwoDigitPadded(int x) {
        String ret = Integer.toString(x);
        if (x < 10) {
            return "0"+ret;
        }

        return ret;
    }

    static void renderUI(Context context) {
        System.err.println("Called render UI!!");
       // TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        table.removeAllViews();
        int index = 0;
        for (Alarm alarm : alarms) {

            TableRow tableRow = new TableRow(context);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

            TextView textView = new TextView(context);
            textView.setTextSize(18);
            textView.setTextColor(Color.WHITE);
            textView.setText("      "+intToStringTwoDigitPadded(alarm.getHour()) + ":" + intToStringTwoDigitPadded(alarm.getMinute()));
            textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

            Switch active = new Switch(context);
            active.setChecked(alarm.isActive());
            active.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1));

            tableRow.addView(textView);
            tableRow.addView(active);

            table.addView(tableRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
            index++;
        }
    }

    static void stopVibrate() {
        vibration.VibrateOff();
    }


    public class AsyncTimeTicker extends AsyncTask<String, String, String> {

        Calendar cal;

        @Override
        protected String doInBackground(String... strings) {
            while( !isCancelled() ) {
                System.out.println(alarms.size());
                cal = Calendar.getInstance();
                Date date = cal.getTime();
                int hour = date.getHours();
                int minute = date.getMinutes();
                for (Alarm alarm : alarms) {
                    if (alarm.getMinute() == minute && alarm.getHour() == hour &&
                            !alarmTriggered && alarm.isActive()) {
                        //vibration.VibrateOn();
                        alarm.setActive(false);
                        alarmTriggered = true;
                        Intent intent = new Intent(getApplicationContext(), AlarmGoing.class);
                        startActivity(intent);
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return "";
        }
    }
}
