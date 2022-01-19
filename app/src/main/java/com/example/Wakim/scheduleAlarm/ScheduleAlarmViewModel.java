package com.example.Wakim.scheduleAlarm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.Wakim.data.Alarm;
import com.example.Wakim.data.AlarmRepository;

import java.util.Calendar;
import java.util.Random;

/**
 * This class is used to call the insert method on the AlarmRepository and passes the Alarm.
 */
public class ScheduleAlarmViewModel extends AndroidViewModel {
    private static AlarmRepository alarmRepository;
    private boolean isNewAlarm;
    private Alarm alarm;

    /**
     * Method that creates a view model for the application.
     *
     * @param application
     */
    public ScheduleAlarmViewModel(@NonNull Application application) {
        super(application);

        alarmRepository = new AlarmRepository(application);
    }

    /**
     * Method that inserts new records to the Room Database.
     *
     * @param hour
     * @param minute
     * @param title
     * @param description
     * @return alarm
     */
    public Alarm save(int hour, int minute, String title, String description) {
        alarm.setHour(hour);
        alarm.setMinute(minute);
        alarm.setTitle(title);
        alarm.setDescription(description);
        alarm.setStarted(true);
        if (isNewAlarm) {
            alarmRepository.insert(alarm);
        } else {
            alarmRepository.update(alarm);
        }
        return alarm;
    }

    /**
     * This method allows us to create a new alarm record or open an existing alarm record.
     *
     * @param alarmId
     * @return
     */
    public LiveData<Alarm> getAlarm(int alarmId) {
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);

        return Transformations.map(alarmRepository.getAlarm(alarmId), alarm -> {
            this.isNewAlarm = alarm == null;
            this.alarm = alarm != null
                    ? alarm
                    : new Alarm(new Random().nextInt(Integer.MAX_VALUE), currentHour, currentMinute);
            return this.alarm;
        });
    }
}
