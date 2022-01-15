package com.example.Wakim.alarmsList;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Wakim.R;
import com.example.Wakim.data.Alarm;
import com.example.Wakim.databinding.ItemAlarmBinding;

import org.jetbrains.annotations.NotNull;

/**
 * This class displays or binds the records from the database to the user Interface
 */
public class AlarmViewHolder extends RecyclerView.ViewHolder {
    private final ItemAlarmBinding binding;
    private final OnManageListener listener;

    public AlarmViewHolder(ItemAlarmBinding binding, OnManageListener listener) {
        super(binding.getRoot());
        this.listener = listener;
        this.binding = binding;
    }


    /**
     * This method is called for each ViewHolder to bind it to the adapter. This is where we will pass our data to our ViewHolder.
     * @param alarm
     */
    public void bind(Alarm alarm) {
        //we will bind the alarms to the item_alarm by taking the data from the alarm class
        String alarmText = String.format("%02d:%02d", alarm.getHour()%12, alarm.getMinute());

        binding.itemAlarmTime.setText(alarmText);
        binding.itemAlarmStarted.setChecked(alarm.isStarted());
        if (alarm.getTitle().length() != 0) {
            binding.itemAlarmTitle.setText(alarm.getTitle());
        }
        else {
            binding.itemAlarmTitle.setText(String.format("%s\n%s ", "Alarm", "No Description"));
        }

        if (alarm.isRecurring()) {
            binding.itemAlarmRecurring.setImageResource(R.drawable.ic_repeat_24dp);
            binding.itemAlarmRecurringDays.setText(alarm.getRecurringDaysString());
        }
        else {
            binding.itemAlarmRecurring.setImageResource(R.drawable.ic_once_24dp);
            binding.itemAlarmRecurringDays.setText("Once");
        }


        binding.itemAlarmStarted.setOnCheckedChangeListener((buttonView, isChecked) -> listener.onToggle(alarm));

        binding.itemAlarmDeleteButton.setOnClickListener(v -> listener.onDelete(alarm));
    }

    public void onViewRecycled() {
        binding.itemAlarmStarted.setOnCheckedChangeListener(null);
    }
}
