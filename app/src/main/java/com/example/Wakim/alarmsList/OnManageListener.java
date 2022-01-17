package com.example.Wakim.alarmsList;

import com.example.Wakim.data.Alarm;

/**
 * This interface listener checks whether the alarm is active or not,
 * for checking if the user clicks the delete icon or click the item alarm layout.
 */
public interface OnManageListener {
    void onToggle(Alarm alarm);
    void onDelete(Alarm alarm);
    void onEdit(Alarm alarm);
}
