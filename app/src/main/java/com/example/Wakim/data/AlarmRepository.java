package com.example.Wakim.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * This is class is used to retrieve Alarm records from the Room database
 */

public class AlarmRepository {
    private AlarmDao alarmDao;
    private LiveData<List<Alarm>> alarmsLiveData;

    public AlarmRepository(Application application) {
        AlarmDatabase db = AlarmDatabase.getDatabase(application);
        alarmDao = db.alarmDao();
        alarmsLiveData = alarmDao.getAlarms();
    }

    /**
     * inserts an element in the Room Database
     * @param alarm
     */
    public void insert(Alarm alarm) {
        AlarmDatabase.databaseWriteExecutor.execute(() -> {
            alarmDao.insert(alarm);
        });
    }

    /**
     * Updates and alarm in the databse
     * @param alarm
     */
    public void update(Alarm alarm) {
        AlarmDatabase.databaseWriteExecutor.execute(() -> {
            alarmDao.update(alarm);
        });
    }

    public void delete(Alarm alarm) {
        AlarmDatabase.databaseWriteExecutor.execute(() -> {
            alarmDao.delete(alarm);
        });
    }

    public LiveData<List<Alarm>> getAlarmsLiveData() {
        return alarmsLiveData;
    }
}
