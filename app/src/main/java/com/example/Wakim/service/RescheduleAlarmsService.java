package com.example.Wakim.service;

import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.Observer;

import com.example.Wakim.data.Alarm;
import com.example.Wakim.data.AlarmRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RescheduleAlarmsService extends LifecycleService {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public IBinder onBind(@NonNull @NotNull Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public int onStartCommand(@Nullable @org.jetbrains.annotations.Nullable Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        AlarmRepository alarmRepository = new AlarmRepository(getApplication());

        alarmRepository.getAlarmsLiveData().observe(this, new Observer<List<Alarm>>() {
            @Override
            public void onChanged(List<Alarm> alarms) {
                for (Alarm a : alarms) {
                    if (a.isStarted()) {
                        a.schedule(getApplicationContext());
                    }
                }
            }
        });

        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
