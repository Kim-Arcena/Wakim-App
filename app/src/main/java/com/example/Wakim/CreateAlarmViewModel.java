package com.example.Wakim;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import org.jetbrains.annotations.NotNull;

public class CreateAlarmViewModel extends AndroidViewModel {
    private static AlarmRepository alarmRepository;

    public CreateAlarmViewModel(@NonNull Application application) {
        super(application);

        alarmRepository = new AlarmRepository(application);
    }

    public static void insert(Alarm alarm) {
        alarmRepository.insert(alarm);
    }
}
