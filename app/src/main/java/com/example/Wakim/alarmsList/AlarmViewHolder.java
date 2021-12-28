package com.example.Wakim.alarmsList;

import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Wakim.R;
import com.example.Wakim.alarmsList.OntoggleAlarmListener;
import com.example.Wakim.data.Alarm;

import org.jetbrains.annotations.NotNull;

public class AlarmViewHolder extends RecyclerView.ViewHolder {
    public Switch alarmStarted;
    //declare variables
    private TextView alarmTime;
    private ImageView alarmRecurring;
    private TextView alarmRecurringDays;
    private TextView alarmTitle;

    //toggle listener
    private OntoggleAlarmListener listener;


    public AlarmViewHolder(@NonNull @NotNull View itemView, OntoggleAlarmListener listener) {
        super(itemView);
        //View binding
        alarmTime = itemView.findViewById(R.id.item_alarm_time);
        alarmRecurring = itemView.findViewById(R.id.item_alarm_recurring);
        alarmRecurringDays = itemView.findViewById(R.id.item_alarm_recurringDays);
        alarmTitle = itemView.findViewById(R.id.item_alarm_title);
        alarmStarted = itemView.findViewById(R.id.item_alarm_started);

        this.listener = listener;

    }

    public void bind(Alarm alarm) {
        //we will bind the alarms to the item_alarm by taking the data from the alarm class
        String alarmText = String.format("%02d:%02d", alarm.getHour(), alarm.getMinute());

        alarmTime.setText(alarmText);

    }
}
