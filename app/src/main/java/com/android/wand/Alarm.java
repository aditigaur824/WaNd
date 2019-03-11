package com.android.wand;

public class Alarm {

    boolean active;
    int minute, hour;

    Alarm(int hour, int minute) {
        this.minute = minute;
        this.hour = hour;
        active = true;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getMinute() {
        return minute;
    }

    public int getHour() {
        return hour;
    }
}
