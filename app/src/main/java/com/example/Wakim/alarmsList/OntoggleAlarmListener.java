package com.example.Wakim.alarmsList;

import com.example.Wakim.data.Alarm;

/**
 * This interface listener checks whether the alarm is active or not
 */
public interface OntoggleAlarmListener {
    void onToggle(Alarm alarm);
}
