package com.example.Wakim;

import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleService;

import org.jetbrains.annotations.NotNull;

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
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
