package com.example.Wakim.scheduleAlarm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.Wakim.data.Alarm;
import com.example.Wakim.data.AlarmRepository;

/**
 * This class is used to call the insert method on the AlarmRepository and passes the Alarm.
 */
public class ScheduleAlarmViewModel extends AndroidViewModel {
    private static AlarmRepository alarmRepository;

    /**
     * Method that creates a view model for the application
     * @param application
     */
    public ScheduleAlarmViewModel(@NonNull Application application) {
        super(application);

        alarmRepository = new AlarmRepository(application);
    }

    /**
     * Method that inserts new records to the Room Database
     * @param alarm
     */
    public static void insert(Alarm alarm) {
        alarmRepository.insert(alarm);
    }
}
