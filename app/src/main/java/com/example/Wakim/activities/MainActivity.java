package com.example.Wakim.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Wakim.R;

/**
 * This is the Main Activity
 * @author Kimberly Villaoslada Arceña
 * @version 1.0
 * This contains the fragments for listing the alarm and creating new alarm using navigation component.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}