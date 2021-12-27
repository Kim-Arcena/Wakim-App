package com.example.Wakim.createAlarm;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.Wakim.AlarmDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AlarmDatabase extends RoomDatabase {
    public abstract AlarmDao alarmDao();

    private static volatile AlarmDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     *
     * @param context
     * @return
     */
    static AlarmDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AlarmDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AlarmDatabase.class,
                            "alarm_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
