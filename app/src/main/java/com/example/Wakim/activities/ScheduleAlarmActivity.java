package com.example.Wakim.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.Wakim.databinding.ActivityScheduleAlarmBinding;

/**
 * Binds the fragment for ScheduleAlarm  since it is going to be reused during updating an alarm
 */
public class ScheduleAlarmActivity extends AppCompatActivity {

    private ActivityScheduleAlarmBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScheduleAlarmBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

}