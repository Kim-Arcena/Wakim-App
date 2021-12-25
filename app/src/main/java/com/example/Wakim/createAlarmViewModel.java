package com.example.Wakim;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import org.jetbrains.annotations.NotNull;

public class createAlarmViewModel extends AndroidViewModel {
    public createAlarmViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public static void insert(Alarm alarm) {
    }
}
