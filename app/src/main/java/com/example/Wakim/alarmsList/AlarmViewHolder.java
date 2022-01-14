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

import org.jetbrains.annotations.NotNull;

/**
 * This class displays or binds the records from the database to the user Interface
 */
public class AlarmViewHolder extends RecyclerView.ViewHolder {
    //declare variables
    private TextView alarmTime;
    private ImageView alarmRecurring;
    private TextView alarmRecurringDays;
    private TextView alarmTitle;
    private Button alarmDeleteButton;
    Switch alarmStarted;

    //toggle listener
    private OnManageListener listener;

    /**
     * Extend this class if you want to hold the child views of a given view, i.e it can be used to hold the
     * views of a Fragment after inflating it's layout. Annotate the view fields with ViewId, i.e @ViewId(R.id.textView1).
     * It will look for the viewId's in the given parent view and will assign the views to the annotated fields of the subclass.
     * @param itemView
     * @param listener
     */
    public AlarmViewHolder(@NonNull @NotNull View itemView, OnManageListener listener) {
        super(itemView);
        //View binding
        alarmTime = itemView.findViewById(R.id.item_alarm_time);
        alarmRecurring = itemView.findViewById(R.id.item_alarm_recurring);
        alarmRecurringDays = itemView.findViewById(R.id.item_alarm_recurringDays);
        alarmTitle = itemView.findViewById(R.id.item_alarm_title);
        alarmStarted = itemView.findViewById(R.id.item_alarm_started);
        alarmDeleteButton = itemView.findViewById(R.id.item_alarm_delete_button);

        this.listener = listener;

    }


    /**
     * This method is called for each ViewHolder to bind it to the adapter. This is where we will pass our data to our ViewHolder.
     * @param alarm
     */
    public void bind(Alarm alarm) {
        //we will bind the alarms to the item_alarm by taking the data from the alarm class
        String alarmText = String.format("%02d:%02d", alarm.getHour()%12, alarm.getMinute());

        alarmTime.setText(alarmText);
        alarmStarted.setChecked(alarm.isStarted());

        if (alarm.getTitle().length() != 0) {
            alarmTitle.setText(String.format("%s | %s | ID - %d ", alarm.getTitle(), alarm.getDescription(), alarm.getAlarmId()));
        }
        else {
            alarmTitle.setText(String.format("%s | ID - %d ", "Alarm", alarm.getAlarmId()));
        }
        if (alarm.isRecurring()) {
            alarmRecurring.setImageResource(R.drawable.ic_repeat_24dp);
            alarmRecurringDays.setText(alarm.getRecurringDaysString());
        } else {
            alarmRecurring.setImageResource(R.drawable.ic_once_24dp);
            alarmRecurringDays.setText("Once");
        }

        alarmStarted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listener.onToggle(alarm);
            }
        });

        alarmDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDelete(alarm);
            }
        });
    }
}
