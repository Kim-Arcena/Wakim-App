package com.example.Wakim.alarmsList;

import com.example.Wakim.data.Alarm;

/**
 * This interface listener checks whether the alarm is active or not
 */
public interface OnManageListener {
    void onToggle(Alarm alarm);
    void onDelete(Alarm alarm);
}
