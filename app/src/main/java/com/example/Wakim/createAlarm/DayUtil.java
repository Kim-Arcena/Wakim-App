package com.example.Wakim.createAlarm;

import java.util.Calendar;

public final class DayUtil {
    //converts Calendar.DAY_OF_WEEK - (int) to String

    /**
     *
     * @param day
     * @return String of the day
     * @throws Exception
     */
    public static final String today(int day) throws Exception{
        switch (day){
            case Calendar.SUNDAY:
                return "Sunday";
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wednesday";
            case Calendar.THURSDAY:
                return "Thursday";
            case Calendar.FRIDAY:
                return "Friday";
            case Calendar.SATURDAY:
                return "Saturday";
        }
        throw new Exception("Could not locate day.");
    }
}
