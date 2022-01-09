package com.example.Wakim.createAlarm;

import android.os.Build;
import android.widget.TimePicker;

/**
 * final class for checking the API level(since before android 6-Marshmallow, the methods where
 *  getCurrentHour() and getCurrentMinute() rather than the getHour() and getMinute() now)
 */
public final class TimePickerUtil {
    public  static  int getTimePickerHour(TimePicker timePicker){
        //Build.VERSION.SDK_INT - SDK version of the hardware device
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return timePicker.getHour();
        }
        else{
            return timePicker.getCurrentHour();
        }
    }

    public  static  int getTimePickerMinute(TimePicker timePicker){
        //Build.VERSION.SDK_INT - SDK version of the hardware device
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return timePicker.getMinute();
        }
        else{
            return timePicker.getCurrentMinute();
        }
    }

}
