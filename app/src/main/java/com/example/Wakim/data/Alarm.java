package com.example.Wakim.data;


import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.Wakim.broadcastReceiver.AlarmBroadcastReceiver;
import com.example.Wakim.scheduleAlarm.DayUtil;

import java.util.Calendar;

import static com.example.Wakim.broadcastReceiver.AlarmBroadcastReceiver.DESCRIPTION;
import static com.example.Wakim.broadcastReceiver.AlarmBroadcastReceiver.FRIDAY;
import static com.example.Wakim.broadcastReceiver.AlarmBroadcastReceiver.MONDAY;
import static com.example.Wakim.broadcastReceiver.AlarmBroadcastReceiver.RECURRING;
import static com.example.Wakim.broadcastReceiver.AlarmBroadcastReceiver.SATURDAY;
import static com.example.Wakim.broadcastReceiver.AlarmBroadcastReceiver.SUNDAY;
import static com.example.Wakim.broadcastReceiver.AlarmBroadcastReceiver.THURSDAY;
import static com.example.Wakim.broadcastReceiver.AlarmBroadcastReceiver.TITLE;
import static com.example.Wakim.broadcastReceiver.AlarmBroadcastReceiver.TUESDAY;
import static com.example.Wakim.broadcastReceiver.AlarmBroadcastReceiver.WEDNESDAY;

/**
 * This class is use to model an alarm as records in the database
 */
@Entity(tableName = "alarm_table")
public class Alarm {
    /**
     *The variables defines the title, description, hour and minute that it will ring,
     * if it schedule or not, or if it is recurring or not. If it is recurring, what days of the weeks it is active
     */
    @PrimaryKey
    @NonNull
    private int alarmId;

    private int hour, minute;
    private boolean started, recurring;
    private boolean monday, tuesday, wednesday, thursday, friday, saturday, sunday;
    private String title, description;

    private long created;

    public Alarm(int alarmId, int hour, int minute) {
        this.alarmId = alarmId;
        this.hour = hour;
        this.minute = minute;
        this.created = System.currentTimeMillis();
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public boolean isStarted() {
        return started;
    }

    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public boolean isMonday() {
        return monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public boolean isFriday() {
        return friday;
    }

    public boolean isSaturday() {
        return saturday;
    }

    public boolean isSunday() {
        return sunday;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
        if (!recurring) {
            monday = tuesday = wednesday = thursday = friday = saturday = sunday = false;
        }
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }

    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    /**
     * This schedule method should be invoked right after the alarm information are supplied in the CreateAlarmFragment method.
     *
     * @param context
     * The schedule method creates an Intent for AlarmBroadcastReceiver and provides data about the Alarm as Intent Extras.
     * It creates the PendingIntent using the Intent previously created.
     */
    @SuppressLint("DefaultLocale")
    public void schedule(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
        intent.putExtra(RECURRING, recurring);
        intent.putExtra(MONDAY, monday);
        intent.putExtra(TUESDAY, tuesday);
        intent.putExtra(WEDNESDAY, wednesday);
        intent.putExtra(THURSDAY, thursday);
        intent.putExtra(FRIDAY, friday);
        intent.putExtra(SATURDAY, saturday);
        intent.putExtra(SUNDAY, sunday);
        intent.putExtra(TITLE, title);
        intent.putExtra(DESCRIPTION, description);

        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, alarmId, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        //check if the alarm has already passed. If it does, increment day by 1
        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        }
        if (!recurring) {
            String toastText = null;
            try {
                toastText = String.format("One Time Alarm scheduled for %s at %02d:%02d %s",
                        DayUtil.toStringDay(calendar.get(Calendar.DAY_OF_WEEK)), hour%12, minute, ((hour>=12) ? "PM" : "AM"), alarmId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(context, toastText, Toast.LENGTH_LONG).show();
            alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    alarmPendingIntent
            );
        }
        else {
            final long RUN_DAILY = 24 * 60 * 60 * 1000;
            alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    RUN_DAILY,
                    alarmPendingIntent
            );
        }

        this.started = true;
    }

    /**
     * This method is used whenever the Toast is shown after the alarm is created
     *
     * @return abbreviation of days
     */
    public String getRecurringDaysString() {
        if(!recurring){
            return null;
        }

        String days = "";
        if(monday){
            days += "Mon ";
        }
        if(tuesday){
            days += "Tue ";
        }
        if(wednesday){
            days += "Wed ";
        }
        if(thursday){
            days += "Thurs ";
        }
        if(friday){
            days += "Fri ";
        }
        if(saturday){
            days += "Sat ";
        }
        if(sunday){
            days += "Sun ";
        }
        return days;
    }

    /**
     * This method is used to cancel the Alarm
     *
     * @param context
     * It takes a reference to the AlarmManager by calling getSystemService(Context.ALARM_SERVICE).
     * Then it create an Intent that uses the AlarmBroadcastReceiver and use the Intent to create a PendingIntent
     * that has a reference to the alarm id that we when scheduling the alarm.
     */
    public void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, alarmId, intent, 0);
        alarmManager.cancel(alarmPendingIntent);
        this.started = false;

        //notify user via toast
        String toastText = String.format("Cancelled Alarm scheduled at %02d:%02d %s", hour%12, minute, ((hour>=12) ? "PM" : "AM"));
        Toast.makeText(context, toastText, Toast.LENGTH_LONG).show();
        Log.i("cancel", toastText);

    }
}
